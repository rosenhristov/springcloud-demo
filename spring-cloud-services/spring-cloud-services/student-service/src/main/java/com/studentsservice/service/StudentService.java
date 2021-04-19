package com.studentsservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.datastax.driver.core.utils.UUIDs;
import com.studentsservice.entity.AddressEntity;
import com.studentsservice.entity.StudentEntity;
import com.studentsservice.entity.StudentPrimaryKey;
import com.studentsservice.exception.StudentNotFoundException;
import com.studentsservice.model.Major;
import com.studentsservice.model.Student;
import com.studentsservice.model.builder.Builders;
import com.studentsservice.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private Tracer tracer;

	public Student fetchStudentDetails(String id, String lastName) {
		StudentPrimaryKey primaryKey = new StudentPrimaryKey();
		primaryKey.setId(id);
		primaryKey.setLastName(lastName);
		StudentEntity entity = repository.findOne(primaryKey);
		if (ObjectUtils.isEmpty(entity)) {
			throw new StudentNotFoundException();
		}
		return mapEntityToModel(entity);
	}

	@ContinueSpan
	public Student insertStudentRecord(Student student) {
		Span span = tracer.createSpan("insertStudentRecord");
		StudentEntity entity = mapModelToEntity(student);
		Student response = mapEntityToModel(repository.save(entity));
		tracer.close(span);
		return response;
	}

	@ContinueSpan
	public StudentEntity mapModelToEntity(Student student) {
		Span span = tracer.createSpan("mapModelToEntity");
		StudentPrimaryKey primaryKey = new StudentPrimaryKey();
		primaryKey.setId(student.getId());
		primaryKey.setLastName(student.getLastName());
		StudentEntity entity = new StudentEntity();
		entity.setAge(student.getAge());
		entity.setMajor(student.getMajor().value());
		entity.setFirstName(student.getFirstName());
		entity.setTimeStamp(new Date());
		entity.setUniqueId(UUIDs.timeBased());
		entity.setPrimaryKey(primaryKey);
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setCity(student.getAddress().getCity());
		addressEntity.setDoorNo(student.getAddress().getDoorNo());
		addressEntity.setState(student.getAddress().getState());
		addressEntity.setStreet(student.getAddress().getStreet());
		addressEntity.setZipCode(student.getAddress().getZipCode());
		entity.setAddress(addressEntity);
		tracer.close(span);
		return entity;
	}

	@ContinueSpan
	public Student mapEntityToModel(StudentEntity entity) {
		Span span = tracer.createSpan("mapEntityToModel");
		Student student = Builders.student()
				.withStudentId(entity.getPrimaryKey().getId())
				.withFirstName(entity.getFirstName())
				.withLastName(entity.getPrimaryKey().getLastName())
				.withAge(entity.getAge())
				.withMajor(Major.valueOf(entity.getMajor().toUpperCase()))
				.fromAddress(Builders.address()
								.withDoorNo(entity.getAddress().getDoorNo())
								.fromStreet(entity.getAddress().getStreet()).fromCity(entity.getAddress().getCity())
								.fromState(entity.getAddress().getState()).withZipCode(entity.getAddress().getZipCode())
								.build())
				.returnResponseMessage(Builders.responseMessage()
											.withResponseCode("success")
											.andResponseMsg("record added to database")
											.onTransactionDate(entity.getTimeStamp().toString())
											.withTransactionId(entity.getUniqueId().toString()).build())
											.build();
		tracer.close(span);
		return student;
	}
}