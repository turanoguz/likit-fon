package com.kogus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kogus.entitiy.Customer;

public interface CustomerRespository extends JpaRepository<Customer, Integer>{

}
