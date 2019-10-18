package com.emperios.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.emperios.test.entity.UserPasswordReset.USER_PASSWORD_RESET;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = USER_PASSWORD_RESET)
public class UserPasswordReset extends AbstractTimestampEntity {
	static final String USER_PASSWORD_RESET = "user_password_reset";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "reset_token", unique = true, length = 36)
	private String passwordResetToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "token_expiration", nullable = false)
	private Date tokenExpirationDate;

	@Column(name = "is_consumed", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean consumed = false;
}
