package com.kogus.controller;

import com.kogus.dto.IndividualCustomerRequest;
import com.kogus.entitiy.IndividualCustomer;
import com.kogus.result.DataResult;
import com.kogus.result.Result;
import com.kogus.result.SuccesDataResult;
import com.kogus.service.abstracts.IndividualCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers/individual")
@RequiredArgsConstructor
public class IndividualCustomerController {

	private final IndividualCustomerService individualCustomerService;

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Result> addFund(@Valid @RequestBody IndividualCustomerRequest individualCustomerRequest) {

		return individualCustomerService.addIndividualCustomer(individualCustomerRequest);
	}

	@GetMapping("/get/all")
	public ResponseEntity<DataResult<List<IndividualCustomer>>> getAll() {
		return this.individualCustomerService.getAll();
	}
	
	
	@GetMapping("/get/{identificationNumber}")
	public ResponseEntity<DataResult<IndividualCustomer>> getByIdentificationNumber(@PathVariable String identificationNumber) {
		
		return this.individualCustomerService.getByIdentificationNumber(identificationNumber);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Result> updateIndividualCustomer(@PathVariable Integer id, @RequestBody IndividualCustomerRequest customerRequest) {
		return this.individualCustomerService.updateIndividualCustomer(id, customerRequest);
	}
	
	
	@PutMapping("/add/cash")
	public ResponseEntity<Result> addCash(@RequestParam String identityNumber,@RequestParam double amount) {
		return this.individualCustomerService.addCash(identityNumber, amount);
		
	}

	@PutMapping("/cash/transfer")
	public ResponseEntity<Result> cashTransfer(@RequestParam String identityNumber, @RequestParam double amount) {
		return individualCustomerService.cashTransfer(identityNumber, amount);
	}
	
	@PutMapping("/update/setActive")
	public ResponseEntity<Result> setActive(@RequestParam String identificationNumber){
		return this.individualCustomerService.setActive(identificationNumber);
	}
	
	@GetMapping("/get/customerNumber/{customerNo}")
	public ResponseEntity<DataResult<IndividualCustomer>> getByCustomerNubmer(@PathVariable String customerNo){
		return this.individualCustomerService.getByCustomerNubmer(customerNo);
	}
	
	
	
}
