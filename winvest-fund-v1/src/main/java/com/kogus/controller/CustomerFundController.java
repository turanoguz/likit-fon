package com.kogus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kogus.dto.CustomerFundRequest;
import com.kogus.result.Result;
import com.kogus.service.abstracts.CustomerFundService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/CustomerFunds")
@RequiredArgsConstructor
public class CustomerFundController {

	private final CustomerFundService customerFundService;
	
	@PutMapping("/buyFund")
	public ResponseEntity<Result> buyFund(@RequestBody CustomerFundRequest fundRequest){
		return this.customerFundService.buyFund(fundRequest);
	}

	@PutMapping("/sell/fund")
	public ResponseEntity<Result> sellFund(@RequestBody CustomerFundRequest fundRequest) {
		return customerFundService.sellFund(fundRequest);
	}


}
