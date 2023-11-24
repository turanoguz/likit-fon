package com.kogus.repository;


import com.kogus.entitiy.IndividualCustomer;

import jakarta.persistence.QueryHint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer>  {
	
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    boolean existsByIdentificationNumber(String identificationNumber);
	
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<IndividualCustomer> findAll();
	
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<IndividualCustomer> findByIsActiveTrue();

	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	IndividualCustomer getByIdentificationNumber(String number);

	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	boolean existsByCustomerNo(String uniqueNumber);

	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	IndividualCustomer getByCustomerNo(String number);

	

	

}
