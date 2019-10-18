package com.emperios.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.emperios.test.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(@Param("username") String username);

	User findByUuid(@Param("uuid") String uuid);
}
