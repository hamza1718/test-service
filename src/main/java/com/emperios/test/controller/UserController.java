package com.emperios.test.controller;

import static com.emperios.test.constants.Constants.APIPaths.USER_API_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emperios.test.entity.User;
import com.emperios.test.repository.UserRepository;

@RestController
@RequestMapping(value = USER_API_PATH, produces = APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserRepository userDao;

	@PostMapping
	public ResponseEntity<UserWS> createUser(@Valid @RequestBody UserRequestWS userRequestWS) {
		return ResponseEntity.ok(create(userRequestWS));
	}

	@GetMapping(path = "/{userUuid}")
	public ResponseEntity<UserWS> getUser(@PathVariable(value = "userUuid") String userUuid) {
		return ResponseEntity.ok(response(userDao.findByUuid(userUuid)));
	}

	private UserWS create(UserRequestWS userRequestWS) {
		User createUser = new User();
		createUser.setUuid(UUID.randomUUID().toString());
		createUser.setUsername(userRequestWS.getUsername());
		createUser.setPassword(userRequestWS.getPassword());
		createUser.setFirstName(userRequestWS.getFirstName());
		createUser.setLastName(userRequestWS.getLastName());
		createUser.setEnabled(true);
		createUser.setSuperuser(userRequestWS.isSuperuser());

		return response(userDao.save(createUser));
	}

	private UserWS response(User user) {
		UserWS userWS = new UserWS();
		userWS.setFirstName(user.getFirstName());
		userWS.setLastName(user.getLastName());
		userWS.setUsername(user.getUsername());
		userWS.setUuid(user.getUuid());

		return userWS;
	}
}
