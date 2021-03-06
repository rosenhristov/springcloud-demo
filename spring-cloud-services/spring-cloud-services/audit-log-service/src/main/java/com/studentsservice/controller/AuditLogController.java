package com.studentsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;
import com.studentsservice.model.AuditLog;
import com.studentsservice.repository.AuditLogRepository;

@RestController
@RequestMapping("/auditLog")
public class AuditLogController {

	@Autowired
	private AuditLogRepository repository;

	@PostMapping
	public void logRequest(@RequestBody AuditLog auditLog) {
		auditLog.setId(UUIDs.timeBased());
		repository.save(auditLog);
	}
}
