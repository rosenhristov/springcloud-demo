package com.studentsservice.validator;

public interface ValidationRegistry {
	public <T> ValidationInterface<T> getValidationService(String serviceName);
}