package com.kogus.service;

import com.kogus.entitiy.Account;
import com.kogus.repository.AccountRepository;
import com.kogus.result.DataResult;
import com.kogus.result.SuccesDataResult;
import com.kogus.service.abstracts.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    
	@Override
	public ResponseEntity<DataResult<Account>> getAccount(int id) {
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new SuccesDataResult<Account>(this.accountRepository.getAccountByCustomerId(id)));
	}
}
