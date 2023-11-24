package com.kogus.controller;

import java.util.List;


import com.kogus.entitiy.FundLog;
import com.kogus.service.LogFundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.kogus.dto.FundRequest;
import com.kogus.entitiy.Fund;
import com.kogus.result.DataResult;
import com.kogus.result.Result;
import com.kogus.service.abstracts.FundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/funds")
@RequiredArgsConstructor
public class FundController {

	private final FundService fundService;
	private final LogFundService findMostValuedFundService;

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Result> addFund(@Valid @RequestBody FundRequest fundRequest) {
		return this.fundService.addFund(fundRequest);
	}

	@GetMapping("/get/all")
	public ResponseEntity<DataResult<List<Fund>>> getAll() {
		return this.fundService.getAll();
	}

	@GetMapping("/get/most-valued/")
	public List<FundLog> findMostValuedFund() {
		return findMostValuedFundService.findMostValuedFund();
	}

	@GetMapping("/get/week/{fundId}")
	public List<FundLog> getAll(@PathVariable String fundId) {
		return findMostValuedFundService.getWeekFunds(fundId);
	}

	@GetMapping("/get/{fundCode}")
	public ResponseEntity<DataResult<Fund>> getByFundCode(@PathVariable String fundCode) {
		return this.fundService.getByFundCode(fundCode);
	}

}
