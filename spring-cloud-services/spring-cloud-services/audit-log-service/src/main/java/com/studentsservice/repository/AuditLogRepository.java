package com.studentsservice.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studentsservice.model.AuditLog;

@Repository
public interface AuditLogRepository extends CrudRepository<AuditLog, Serializable> {

}