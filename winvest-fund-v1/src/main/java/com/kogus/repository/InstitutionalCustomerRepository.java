package com.kogus.repository;

import com.kogus.entitiy.InstitutionalCustomer;

import jakarta.persistence.QueryHint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionalCustomerRepository extends JpaRepository<InstitutionalCustomer, Integer> {

	
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    boolean existsByCustomerTaxNo(String customerTaxNo);

	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<InstitutionalCustomer> findAll();
	
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<InstitutionalCustomer> findByIsActiveTrue();

	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	InstitutionalCustomer getByCustomerTaxNo(String number);

	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	boolean existsByCustomerNo(String number);

	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	InstitutionalCustomer getByCustomerNo(String number);

	
	
	
	
}
