package com.emperios.test.controller;

import lombok.Data;

@Data
public class PasswordResetRequestWS {

	private String emailAddress;
	private String newPassword;
}
