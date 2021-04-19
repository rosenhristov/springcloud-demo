package com.studentsservice.validator;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.studentsservice.model.Error;
import com.studentsservice.model.Major;
import com.studentsservice.property.Property;

@Service
public class EnumValidator implements ValidationInterface<Major> {

	@Override
	public void invokeValidationOn(Major major, Errors errors, Property property) {
		if (!(major == null) && major == Major.UNKNOWN_ENUM)
			errors.rejectValue(property.getField(), Error.INVALID_ERROR_CODE.value(),
					Error.INVALID_ERROR_MSG.value().concat(property.getField()));
	}

}
