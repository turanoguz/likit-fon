package com.kogus.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kogus.dto.FundRequest;
import com.kogus.entitiy.Fund;
import com.kogus.repository.FundRepository;
import com.kogus.result.DataResult;
import com.kogus.result.ErrorResult;
import com.kogus.result.Result;
import com.kogus.result.SuccesDataResult;
import com.kogus.result.SuccesResult;
import com.kogus.service.abstracts.FundService;
import com.kogus.utils.ReflectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FundServiceImpl implements FundService {

	private final FundRepository fundRepository;

	@Override
	public ResponseEntity<Result> addFund(FundRequest fundRequest) {
		return fundControl(fundRequest);
	}

	private ResponseEntity<Result> fundControl(FundRequest fundRequest) {
		fundRequest.setFundCode(fundRequest.getFundCode().toUpperCase());
		fundRequest.setFundName(fundRequest.getFundName().toUpperCase()); 
		if (fundRepository.existsByFundName(fundRequest.getFundName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Fon Adı sistemde Zaten Kayıtlı..!"));
		}
		if (fundRepository.existsByFundCode(fundRequest.getFundCode())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Fon Kodu sistemde Zaten Kayıtlı..!"));
		}
		Fund fund = ReflectionUtils.cast(fundRequest, Fund.class);
		fund.setFundDate(new Date());
		this.fundRepository.save(fund);
		log.info("Class : " + getClass().getName() + "-> " + "Fon eklendi. Fon ID: " + fund.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(new SuccesResult("Fon Başarıyla Kayıt Edildi..!"));
	}

	@Override
	public ResponseEntity<DataResult<List<Fund>>> getAll() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new SuccesDataResult<List<Fund>>(this.fundRepository.findAll()));
	}

	@Override
	public ResponseEntity<DataResult<Fund>> getByFundCode(String fundCode) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new SuccesDataResult<Fund>(this.fundRepository.getFundByFundCode(fundCode)));
	}

}
