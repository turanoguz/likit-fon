package com.kogus.controller;

import com.kogus.dto.InstitutionalCustomerRequest;
import com.kogus.dto.InstitutionalCustomerResponse;
import com.kogus.entitiy.InstitutionalCustomer;
import com.kogus.result.DataResult;
import com.kogus.result.Result;
import com.kogus.service.abstracts.InstitutionalCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers/institutional")
@RequiredArgsConstructor
public class InstitutionalCustomerController {

	private final InstitutionalCustomerService institutionalCustomerService;

	@PostMapping("/add")
	public ResponseEntity<Result> addFund(
			@Valid @RequestBody InstitutionalCustomerRequest institutionalCustomerRequest) {

		return institutionalCustomerService.addInstitutionalCustomer(institutionalCustomerRequest);
	}

	@GetMapping("/get/all")
	public ResponseEntity<DataResult<List<InstitutionalCustomer>>> getAll() {

		return this.institutionalCustomerService.getAll();
				
	
	}
	
	@GetMapping("/get/{customerTaxNo}")
	public ResponseEntity<DataResult<InstitutionalCustomer>> getBytaxNo(@PathVariable String customerTaxNo){
		
		return this.institutionalCustomerService.getBytaxNo(customerTaxNo);
		
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Result> updateInstitutionalCustomer(@PathVariable Integer id, @RequestBody InstitutionalCustomerResponse customerRequest){
		return this.institutionalCustomerService.updateInstitutionalCustomer(id, customerRequest);
		
	}
	
	@PutMapping("/add/cash")
	public ResponseEntity<Result> addCash(String customerTaxNo, double amount){
		return this.institutionalCustomerService.addCash(customerTaxNo, amount);
		
	}

	@PutMapping("/cash/transfer")
	public ResponseEntity<Result> cashTransfer(@RequestParam String customerTaxNo, @RequestParam double amount) {
		return institutionalCustomerService.cashTransfer(customerTaxNo, amount);
	}
	
	@PutMapping("update/setActive")
	public ResponseEntity<Result> setActive(String taxNo){
		
		return this.institutionalCustomerService.setActive(taxNo);
	}
	
	@GetMapping("/get/customerNumber/{customerNo}")
	public ResponseEntity<DataResult<InstitutionalCustomer>> getByCustomerNubmer(@PathVariable String customerNo){
		return this.institutionalCustomerService.getByCustomerNubmer(customerNo);
	}
	
	
		

}
