package com.studentsservice.validator;

import java.util.List;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.studentsservice.model.Address;
import com.studentsservice.property.AddressProperty;
import com.studentsservice.property.Property;

@Component
public class AddressValidator implements Validator {

	@Autowired
	private ValidationRegistry registry;

	@Autowired
	private AddressProperty addressProperty;
	
	@Autowired
	private Tracer tracer;

	@Override
	public boolean supports(Class<?> arg0) {
		return Address.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Span span = tracer.createSpan("validate-address");
		final Address address = (Address) target;
		final List<Property> properties = addressProperty.getProperties();
		properties.forEach(
				property -> registry
								.getValidationService(property.getValidator())
									.invokeValidationOn(
										new BeanWrapperImpl(address).getPropertyValue(property.getField()),
										errors,
										property));
		errors.popNestedPath();
		tracer.close(span);
	}
}