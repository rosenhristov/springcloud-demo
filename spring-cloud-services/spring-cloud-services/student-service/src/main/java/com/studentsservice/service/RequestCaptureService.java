package com.studentsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;
import com.studentsservice.entity.AddressEntity;
import com.studentsservice.model.Address;
import com.studentsservice.model.Student;
import com.studentsservice.repository.RequestCaptureRepository;

@Service
public class RequestCaptureService {

	@Autowired
	private RequestCaptureRepository repository;
	
	@Autowired
	private Tracer tracer;

	public boolean captureIncomingRequests(Student student) {
		Span span = tracer.createSpan("captureIncomingRequests");
		Address address = student.getAddress();
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setCity(address.getCity());
		addressEntity.setDoorNo(address.getDoorNo());
		addressEntity.setState(address.getState());
		addressEntity.setStreet(address.getStreet());
		addressEntity.setZipCode(address.getZipCode());
		tracer.close(span);
		return repository.captureIncomingRequest(student.getId(), student.getLastName(), addressEntity,
				student.getAge(), student.getFirstName(), student.getMajor().value(), UUIDs.timeBased(),
				generateVerifiedValue(student));
	}

	private String generateVerifiedValue(Student student) {
		// TODO Auto-generated method stub
		return student.getId() + student.getAddress().getZipCode().hashCode();
	}
}
