package com.kogus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kogus.entitiy.CustomerFund;

public interface CustomerFundRepository extends JpaRepository<CustomerFund, Integer>{

	boolean existsByFundId(int id);

	CustomerFund getByFundId(int fundId);

	List<CustomerFund> findByCustomerId(int id);
}
