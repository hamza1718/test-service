package com.emperios.test.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class APIPaths {
		public static final String BASE_PATH_V1 = "/api/v1";

		// User
		public static final String USER_API_PATH = BASE_PATH_V1 + "/users";

		// PasswordReset
		public static final String INITIATE_PASSWORD_RESET = "/initiatePasswordReset";
	}
}
