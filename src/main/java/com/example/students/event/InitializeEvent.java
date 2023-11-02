package com.example.students.event;

import com.example.students.service.StudentService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "students.initialize", havingValue = "true")
public class InitializeEvent implements ApplicationListener<ApplicationStartedEvent> {

	private final StudentService studentService;

	public InitializeEvent(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		studentService.initializeStudents();
	}
}
