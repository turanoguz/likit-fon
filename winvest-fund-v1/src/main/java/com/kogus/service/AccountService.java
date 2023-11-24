package com.kogus.service;

import com.kogus.entitiy.Account;
import com.kogus.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> getByCustomerId (Integer id) {
        return accountRepository.findAccountByCustomerId(id);
    }
}
