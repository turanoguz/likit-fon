package com.kogus.service.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kogus.dto.CashRequest;
import com.kogus.dto.CustomerDto;
import com.kogus.dto.CustomerFundsDto;
import com.kogus.entitiy.Customer;
import com.kogus.result.DataResult;
import com.kogus.result.Result;

public interface CustomerService {
	
	ResponseEntity<DataResult<List<Customer>>> getAll();
	
	public ResponseEntity<DataResult<List<CustomerDto>>> getCustomerAccounts(String number);
	
	public ResponseEntity<DataResult<List<CustomerDto>>> getCustomerAccountsByCustomerNo(String number);
	
	public ResponseEntity<DataResult<List<CustomerFundsDto>>> getCustomerFund(String number);
	
	public ResponseEntity<DataResult<List<CustomerFundsDto>>> getCustomerFundByCustomerNo(String number);
	
	public ResponseEntity<Result> addCash(CashRequest addCashRequest);
	
	public ResponseEntity<Result> withdrawMoney(CashRequest request);
}
