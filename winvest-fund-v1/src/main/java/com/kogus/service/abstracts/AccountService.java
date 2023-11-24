package com.kogus.service.abstracts;

import org.springframework.http.ResponseEntity;

import com.kogus.entitiy.Account;
import com.kogus.result.DataResult;

public interface AccountService {
	ResponseEntity<DataResult<Account>> getAccount(int id);

}
