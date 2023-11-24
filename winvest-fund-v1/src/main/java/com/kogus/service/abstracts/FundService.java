package com.kogus.service.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kogus.dto.FundRequest;
import com.kogus.dto.FundResponse;
import com.kogus.entitiy.Fund;
import com.kogus.result.DataResult;
import com.kogus.result.Result;

public interface FundService {
	
	
	ResponseEntity<Result> addFund(FundRequest fundRequest);
	
	ResponseEntity<DataResult<List<Fund>>> getAll();
	
	ResponseEntity<DataResult<Fund>> getByFundCode(String fundCode);
	
	
	
	

}
