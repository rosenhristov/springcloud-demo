package com.studentsservice.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainClass {

	public class Student {

	}

	public static void main(String[] args) {
		List<AuditLog> student = new ArrayList<>();
		Collections.sort(student, (o1, o2) -> {
			// TODO Auto-generated method stub
			return 0;
		});

		student.sort(Comparator.comparing(AuditLog::getEndDate));
		student.stream().forEach(c -> System.out.println(c));
	}

}
