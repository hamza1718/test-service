package com.emperios.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emperios.test.entity.UserPasswordReset;

public interface PasswordResetRequestRepository extends JpaRepository<UserPasswordReset, Long> {

	List<UserPasswordReset> findByUserId(long userId);

	UserPasswordReset findByPasswordResetToken(String passwordResetToken);
}
