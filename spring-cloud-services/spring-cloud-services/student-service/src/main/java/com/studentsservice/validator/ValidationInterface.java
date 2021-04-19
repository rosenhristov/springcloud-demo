package com.studentsservice.validator;

import org.springframework.validation.Errors;

import com.studentsservice.property.Property;

public interface ValidationInterface<T> {
	void invokeValidationOn(T request, Errors errors, Property property);
}