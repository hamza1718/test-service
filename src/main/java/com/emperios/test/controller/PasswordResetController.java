package com.emperios.test.controller;

import static com.emperios.test.constants.Constants.APIPaths.BASE_PATH_V1;
import static com.emperios.test.constants.Constants.APIPaths.INITIATE_PASSWORD_RESET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emperios.test.service.PasswordResetService;

@RestController
@RequestMapping(value = BASE_PATH_V1, produces = APPLICATION_JSON_VALUE)
public class PasswordResetController {

	@Autowired
	private PasswordResetService passwordResetService;

	@PostMapping(INITIATE_PASSWORD_RESET)
	public ResponseEntity<String> initiatePasswordReset(@Valid @RequestBody PasswordResetRequestWS passwordResetRequestWS) {

		passwordResetService.createPasswordResetAndInitiateProcess(passwordResetRequestWS);

		// send a success response even if the user was not found, as a security measure
		// to not give away if the email is registered or not.
		return ResponseEntity.ok().body("Password reset request processed.");
	}
}
