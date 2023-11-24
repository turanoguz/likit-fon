package com.kogus.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kogus.auth.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {
	
	
		Token findByUserId(long userId);	
		
		boolean existsTokenByUserId(Long id);
		
		Optional<Token> findByToken(String token);


}
