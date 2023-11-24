package com.kogus.service.abstracts;

import com.kogus.entitiy.Fund;
import org.springframework.http.ResponseEntity;

import com.kogus.dto.CustomerFundRequest;
import com.kogus.result.Result;

public interface CustomerFundService {
	
	ResponseEntity<Result> buyFund(CustomerFundRequest fundRequest);

    ResponseEntity<Result> sellFund(CustomerFundRequest fundRequest);

}
