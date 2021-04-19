package com.studentsservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studentsservice.entity.StudentEntity;
import com.studentsservice.entity.StudentPrimaryKey;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, StudentPrimaryKey> {
}