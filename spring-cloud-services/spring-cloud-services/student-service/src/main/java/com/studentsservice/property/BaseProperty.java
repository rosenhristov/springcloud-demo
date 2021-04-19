package com.studentsservice.property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Property> properties = new ArrayList<>();

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
}
