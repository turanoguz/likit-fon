package com.kogus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import com.kogus.entitiy.Fund;

import jakarta.persistence.QueryHint;

public interface FundRepository extends JpaRepository<Fund, Integer>{

	
	Fund getById(Integer id);
	boolean existsByFundName(String fundName);
	List<Fund> findAll();
	Fund getFundByFundCode(String fundCode);
	boolean existsByFundCode(String fundCode);
	Optional<Fund> findByFundCode(String fundCode);
}
