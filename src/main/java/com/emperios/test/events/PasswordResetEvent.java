package com.emperios.test.events;

import lombok.Data;

import org.springframework.context.ApplicationEvent;

@Data
public class PasswordResetEvent extends ApplicationEvent {

	private long userId;
	private String username;

	/*
		Controls whether this event is to send the post reset email
	 */
	private boolean postReset = false;

	public PasswordResetEvent(Object source) {
		super(source);
	}
}
