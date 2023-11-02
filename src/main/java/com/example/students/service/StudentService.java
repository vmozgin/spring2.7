package com.example.students.service;

import com.example.students.event.EventHolder;
import com.example.students.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class StudentService {

	private final ApplicationEventPublisher publisher;

	private List<Student> students = new ArrayList<>();

	public StudentService(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@ShellMethod("Add student")
	public String add(String firstName, String lastName, int age) {
		Student student = Student.builder()
				.id(UUID.randomUUID())
				.lastName(lastName)
				.firstName(firstName)
				.age(age)
				.build();
		students.add(student);

		publisher.publishEvent(new EventHolder(this, "Event: add " + student));
		return "Student added: " + firstName + " " + lastName + " (Age: " + age + ")";
	}

	@ShellMethod("Display all students")
	public void print() {
		students.forEach(System.out::println);
	}

	@ShellMethod("Delete student")
	public String del(UUID id) {
		students = students.stream()
				.filter(s -> !s.getId().equals(id))
				.collect(Collectors.toList());
		publisher.publishEvent(new EventHolder(this, "Event: delete student with id = a" + id.toString()));
		return "Success";
	}

	@ShellMethod("Clear students list")
	public String clean() {
		students = new ArrayList<>();
		return "List of students cleared";
	}

	public void initializeStudents() {
		students.add(new Student(UUID.randomUUID(), "Vasya", "Pupkin", 15));
		students.add(new Student(UUID.randomUUID(), "Kirill", "Kleshin", 16));
		students.add(new Student(UUID.randomUUID(), "Stepan", "Ivanov", 23));
		System.out.println("Initialize students complete");
	}
}
