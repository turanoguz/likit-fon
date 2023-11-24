package com.kogus.auth.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tokens")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "user_id")
	private long userId;
	
	
	@NotNull
	@Column(name = "token")
	private String token;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "token_type")
	private TokenType tokenType;
	
	
	@OneToOne
	@JoinColumn(name = "user_id", updatable = false, insertable = false)
	public User user;

}
