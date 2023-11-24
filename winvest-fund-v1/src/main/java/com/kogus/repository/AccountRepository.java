package com.kogus.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import com.kogus.entitiy.Account;

import jakarta.persistence.QueryHint;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Account> findAccountByCustomerId(int id);
	
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	Account getAccountByCustomerId(int id);

	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	Account getByCustomerId(int id);
	
}
