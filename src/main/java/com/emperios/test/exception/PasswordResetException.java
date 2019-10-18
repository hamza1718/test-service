package com.emperios.test.exception;

public class PasswordResetException extends RuntimeException {
	private static final long serialVersionUID = 7865394287l;

	public PasswordResetException(String message) {
		super(message);
	}
}
