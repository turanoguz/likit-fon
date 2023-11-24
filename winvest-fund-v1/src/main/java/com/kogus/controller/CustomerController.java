package com.kogus.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kogus.dto.CashRequest;
import com.kogus.dto.CustomerDto;
import com.kogus.dto.CustomerFundsDto;
import com.kogus.entitiy.Customer;
import com.kogus.result.DataResult;
import com.kogus.result.Result;
import com.kogus.service.abstracts.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers/")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	@GetMapping("/get/All")
	public ResponseEntity<DataResult<List<Customer>>> getAll(){
		
		return this.customerService.getAll();
	}
	
	
	@GetMapping("/get/accounts/{number}")
	public ResponseEntity<DataResult<List<CustomerDto>>> getCustomerAccounts(@PathVariable String number){
		
		return this.customerService.getCustomerAccounts(number);
	}
	
	@GetMapping("/get/funds/{number}")
	public ResponseEntity<DataResult<List<CustomerFundsDto>>> getCustomerFund(@PathVariable String number){
		return this.customerService.getCustomerFund(number);
	}
	
	@GetMapping("/get/funds/customerNo/{customerNo}")
	public ResponseEntity<DataResult<List<CustomerFundsDto>>> getCustomerFundByCustomerNo(@PathVariable String customerNo){
		return this.customerService.getCustomerFundByCustomerNo(customerNo);
	}
	
	@PutMapping("/add/cash")
	public ResponseEntity<Result> addCash(@RequestBody CashRequest cashRequest){
		return this.customerService.addCash(cashRequest);
	}
	
	@PutMapping("/withdraw/money")
	public ResponseEntity<Result> withdrawMoney(@RequestBody CashRequest request){
		return this.customerService.withdrawMoney(request);
	}
	
	@GetMapping("/get/accounts/customer-no/{customerNo}")
	public ResponseEntity<DataResult<List<CustomerDto>>> getCustomerAccountsByCustomerNo(@PathVariable String customerNo){
		return this.customerService.getCustomerAccountsByCustomerNo(customerNo);
	}
}
