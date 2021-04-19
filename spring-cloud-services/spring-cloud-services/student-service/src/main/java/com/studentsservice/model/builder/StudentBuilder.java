package com.studentsservice.model.builder;

import com.studentsservice.model.Address;
import com.studentsservice.model.Major;
import com.studentsservice.model.ResponseMessage;
import com.studentsservice.model.Student;

public class StudentBuilder {

	private Student student = new Student();

	public StudentBuilder withStudentId(String id) {
		student.setId(id);
		return this;
	}

	public StudentBuilder withFirstName(String name) {
		student.setFirstName(name);
		return this;
	}

	public StudentBuilder withLastName(String lastName) {
		student.setLastName(lastName);
		return this;
	}

	public StudentBuilder withAge(String age) {
		student.setAge(age);
		return this;
	}

	public StudentBuilder fromAddress(Address address) {
		student.setAddress(address);
		return this;
	}

	public StudentBuilder withMajor(Major major) {
		student.setMajor(major);
		return this;
	}

	public StudentBuilder returnResponseMessage(ResponseMessage response) {
		student.setResponse(response);
		return this;
	}

	public Student build() {
		return student;
	}
}