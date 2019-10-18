package com.emperios.test.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emperios.test.controller.PasswordResetRequestWS;
import com.emperios.test.entity.User;
import com.emperios.test.entity.UserPasswordReset;
import com.emperios.test.events.PasswordResetEvent;
import com.emperios.test.exception.PasswordResetException;
import com.emperios.test.repository.PasswordResetRequestRepository;
import com.emperios.test.repository.UserRepository;

@Service
public class PasswordResetService {

	@Autowired
	private UserRepository userDao;
	@Autowired
	private PasswordResetRequestRepository passwordResetDao;
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Transactional
	public UserPasswordReset getAndCheckPasswordReset(String resetToken) {
		UserPasswordReset passwordReset = passwordResetDao.findByPasswordResetToken(resetToken);

		if (passwordReset == null) {
			throw new PasswordResetException("Reset request not found for token.");
		}

		if (passwordReset.isConsumed()) {
			throw new PasswordResetException("Reset token has already been consumed.");
		}

		Instant expirationTime = passwordReset.getTokenExpirationDate().toInstant();
		if (Instant.now().isAfter(expirationTime)) {
			throw new PasswordResetException("Reset token expired.");
		}

		return passwordReset;
	}

	@Transactional
	public void createPasswordResetAndInitiateProcess(PasswordResetRequestWS passwordResetRequestWS) {
		User user = userDao.findByUsername(passwordResetRequestWS.getEmailAddress());
		if (user != null) {
			UserPasswordReset passwordReset = new UserPasswordReset();
			passwordReset.setUser(user);
			passwordReset.setPasswordResetToken(UUID.randomUUID().toString());
			passwordReset.setTokenExpirationDate(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)));
			passwordReset.setConsumed(false);

			passwordResetDao.save(passwordReset);

			publishPasswordResetEvent(user, false);
		}
	}

	@Transactional
	public void passwordResetPostProcess(UserPasswordReset userPasswordReset) {
		userPasswordReset.setConsumed(true);
		passwordResetDao.save(userPasswordReset);

		publishPasswordResetEvent(userPasswordReset.getUser(), true);
	}

	private void publishPasswordResetEvent(User user, boolean isPostResetEvent) {
		PasswordResetEvent passwordResetEvent = new PasswordResetEvent(this);
		passwordResetEvent.setUserId(user.getId());
		passwordResetEvent.setUsername(user.getUsername());
		passwordResetEvent.setPostReset(isPostResetEvent);
		eventPublisher.publishEvent(passwordResetEvent);
	}
}
