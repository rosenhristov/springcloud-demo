package com.studentsservice.intfc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.studentsservice.model.AuditLog;

@FeignClient(name = "audit-log-service")
public interface AuditLoggingFeignClient {

	@PostMapping(value = "/auditLog")
	public void auditLogRequest(AuditLog auditLog);
}