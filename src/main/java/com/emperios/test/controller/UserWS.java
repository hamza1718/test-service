package com.emperios.test.controller;

import lombok.Data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserWS implements Serializable {
	private static final long serialVersionUID = 41351589235909L;

	private String firstName;
	private String lastName;
	private String username;
	private String uuid;
}
