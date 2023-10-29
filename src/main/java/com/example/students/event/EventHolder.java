package com.example.students.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolder extends ApplicationEvent {

	private final String event;

	public EventHolder(Object source, String event) {
		super(source);
		this.event = event;
	}
}
