package com.studentsservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.ErrorController;

import com.studentsservice.model.AuditLog;

public class ZuulErrorHandler implements ErrorController {

	@Override
	public String getErrorPath() {
		String s = null;
		List<AuditLog> auditLog = new ArrayList<>();
		
		return null;
	}

}
