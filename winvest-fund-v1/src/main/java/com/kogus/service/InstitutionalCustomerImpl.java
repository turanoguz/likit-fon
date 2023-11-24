package com.kogus.service;


import com.kogus.dto.AccountsDto;
import com.kogus.dto.InstitutionalCustomerRequest;
import com.kogus.dto.InstitutionalCustomerResponse;
import com.kogus.entitiy.Account;
import com.kogus.entitiy.Customer;
import com.kogus.entitiy.InstitutionalCustomer;
import com.kogus.repository.AccountRepository;
import com.kogus.repository.InstitutionalCustomerRepository;
import com.kogus.result.DataResult;
import com.kogus.result.ErrorResult;
import com.kogus.result.Result;
import com.kogus.result.SuccesDataResult;
import com.kogus.result.SuccesResult;
import com.kogus.service.abstracts.InstitutionalCustomerService;
import com.kogus.utils.ReflectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstitutionalCustomerImpl implements InstitutionalCustomerService {

	private final InstitutionalCustomerRepository institutionalCustomerRepository;
	private final AccountRepository accountRepository;

	@Override
	public ResponseEntity<Result> addInstitutionalCustomer(InstitutionalCustomerRequest customerRequest) {
		if (checkIfIdentificationNumberCustomer(customerRequest)) {
			return ResponseEntity.badRequest().body(new ErrorResult("User already exists with this tax no"));
		}

		InstitutionalCustomer InstitutionalCustomer = ReflectionUtils.cast(customerRequest,InstitutionalCustomer.class);
		InstitutionalCustomer.setCustomerNo(Long.toString(generateRandomNumber()));
		InstitutionalCustomer savedInstitutionalCustomer = institutionalCustomerRepository.save(InstitutionalCustomer);
		AccountsDto accountsDto = new AccountsDto();
		accountsDto.setCustomerId(savedInstitutionalCustomer.getId());
		accountsDto.setAccountName(savedInstitutionalCustomer.getCompanyName()+" Birincil Para Hesabı");
		accountsDto.setTotalMoney(0);
		Account reflectAccount = ReflectionUtils.cast(accountsDto, Account.class);
		Account savedAccount = this.accountRepository.save(reflectAccount);
		log.info("Class : " + getClass().getName() + " -> " + " User saved. User tax no : "
				+ customerRequest.getCustomerTaxNo());
		return ResponseEntity.ok().body(new SuccesDataResult<String>(savedInstitutionalCustomer.getCustomerNo() ,"User successfully saved"));
	}
	public long generateRandomNumber() {
		long uniqueNumber;
		Random random = new Random();
        int digitCount = 10;
        long min = (long) Math.pow(10, digitCount - 1);
        long max = (long) Math.pow(10, digitCount) - 1;
        
        do {
            uniqueNumber = min + random.nextInt((int) (max - min + 1));
        } while (this.institutionalCustomerRepository.existsByCustomerNo(Long.toString(uniqueNumber)));        
        return uniqueNumber;
    }

	@Override
	public boolean checkIfIdentificationNumberCustomer(InstitutionalCustomerRequest customerRequest) {
		return institutionalCustomerRepository.existsByCustomerTaxNo(customerRequest.getCustomerTaxNo());

	}

	@Override
	public ResponseEntity<DataResult<List<InstitutionalCustomer>>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<List<InstitutionalCustomer>>(
				this.institutionalCustomerRepository.findByIsActiveTrue()));
	}

	@Override
	public ResponseEntity<DataResult<InstitutionalCustomer>> getBytaxNo(String TaxNo) {
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<InstitutionalCustomer>(
				this.institutionalCustomerRepository.getByCustomerTaxNo(TaxNo)));
	}

	@Override
	public ResponseEntity<Result> updateInstitutionalCustomer(Integer id, InstitutionalCustomerResponse customerRequest) {
		InstitutionalCustomer customer = this.institutionalCustomerRepository.findById(id).orElseThrow(() -> new RuntimeException("Kurumsal müşteri bulunamadı."));
		customer.setCompanyName(customerRequest.getCompanyName());
		customer.setCustomerTaxNo(customerRequest.getCustomerTaxNo());
		customer.setCustomerPhone(customerRequest.getCustomerPhone());
		customer.setEmail(customerRequest.getEmail());
		customer.setActive(customerRequest.isActive());
		InstitutionalCustomer rsavedCustumer = this.institutionalCustomerRepository.save(customer);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesResult("Kullanıcı Başarıyla Güncellendi.."));
	}

	@Override
	public ResponseEntity<Result> addCash(String customerTaxNo, double amount) {
		InstitutionalCustomer customer = institutionalCustomerRepository.getByCustomerTaxNo(customerTaxNo);
		Account account = accountRepository.getById(customer.getId());
		if(account == null) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Müşteriye Ait Bir Banka Hesabı Bulunamadı..."));
		double currentBalance = account.getTotalMoney() + amount;
		account.setTotalMoney(currentBalance);
		Account savedAccount = this.accountRepository.save(account);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesResult("Hesap Bakiyesi Başarıyla Güncellendi.."));
	}

	@Override
	public ResponseEntity<Result> cashTransfer(String customerTaxNo, double amount) {
		InstitutionalCustomer customer = institutionalCustomerRepository.getByCustomerTaxNo(customerTaxNo);
		Account account = accountRepository.getByCustomerId(customer.getId());
		if(account == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Müşteriye Ait Bir Banka Hesabı Bulunamadı..."));
		} else if (account.getTotalMoney() < amount) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Bu işlem için bakiyeniz yetersiz."));
		} else {
			double currentBalance = account.getTotalMoney() - amount ;
			account.setTotalMoney(currentBalance);
			accountRepository.save(account);
			return ResponseEntity.status(HttpStatus.OK).body(new SuccesResult("Hesap Bakiyesi Başarıyla Güncellendi.."));
		}
	}

	@Override
	public ResponseEntity<Result> setActive(String taxNo) {
		InstitutionalCustomer customer = this.institutionalCustomerRepository.getByCustomerTaxNo(taxNo);
		if(customer == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("VKN Numarasına Ait Kullanıcı Bulunamadı.."));
		customer.setActive(false);
		InstitutionalCustomer savedCustomer = this.institutionalCustomerRepository.save(customer);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesResult("Kullanıcı Başarıyla Silindi.."));
	}
	
	@Override
	public ResponseEntity<DataResult<InstitutionalCustomer>> getByCustomerNubmer(String customerNo) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new SuccesDataResult<InstitutionalCustomer>(this.institutionalCustomerRepository.getByCustomerNo(customerNo)));
	}

}
