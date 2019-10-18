package com.emperios.test.entity;

import lombok.Getter;
import lombok.Setter;

import static com.emperios.test.entity.User.USERS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Table(name = USERS)
public class User extends AbstractTimestampEntity {
	static final String USERS = "users";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "uuid", nullable = false, unique = true, updatable = false, length = 36)
	private String uuid;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username", nullable = false, unique = true, updatable = false, length = 45)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled = false;

	@Column(name = "is_superuser", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean superuser = false;
}
