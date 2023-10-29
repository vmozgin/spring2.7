package com.example.students.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("students")
@Data
public class StudentsProperties {

	private Boolean initialize;
}
