package com.kogus.service;

import com.kogus.dto.AccountsDto;
import com.kogus.dto.IndividualCustomerRequest;
import com.kogus.entitiy.Account;
import com.kogus.entitiy.IndividualCustomer;
import com.kogus.repository.AccountRepository;
import com.kogus.repository.IndividualCustomerRepository;
import com.kogus.result.DataResult;
import com.kogus.result.ErrorResult;
import com.kogus.result.Result;
import com.kogus.result.SuccesDataResult;
import com.kogus.result.SuccesResult;
import com.kogus.service.abstracts.IndividualCustomerService;
import com.kogus.utils.ReflectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class IndividualCustomerImpl implements IndividualCustomerService {

	private final IndividualCustomerRepository individualCustomerRepository;
	private final AccountRepository accountRepository;

	@Override
	public ResponseEntity<Result> addIndividualCustomer(IndividualCustomerRequest customerRequest) {
		if (checkIfIdentificationNumberCustomer(customerRequest)) {
			return ResponseEntity.badRequest().body(new ErrorResult("User already exists with this ID card"));
		}
		IndividualCustomer individualCustomer = ReflectionUtils.cast(customerRequest, IndividualCustomer.class);
		individualCustomer.setCustomerNo(Long.toString(generateRandomNumber()));
		IndividualCustomer savedIndividualCustomer = individualCustomerRepository.save(individualCustomer);
		AccountsDto accountsDto = new AccountsDto();
		accountsDto.setCustomerId(savedIndividualCustomer.getId());
		accountsDto.setAccountName(savedIndividualCustomer.getCustomerFirstName()+" Birincil Para Hesabı");
		accountsDto.setTotalMoney(0);
		Account reflectAccount = ReflectionUtils.cast(accountsDto, Account.class);
		Account savedAccount = this.accountRepository.save(reflectAccount);
		log.info("Class : " + getClass().getName() + " -> " + " User saved. User ID card no : "
				+ customerRequest.getIdentificationNumber());
		return ResponseEntity.ok().body(new SuccesDataResult<String>(savedIndividualCustomer.getCustomerNo(),"User successfully saved."));
	}
	
	public long generateRandomNumber() {
		long uniqueNumber;
		Random random = new Random();
        int digitCount = 10;
        long min = (long) Math.pow(10, digitCount - 1);
        long max = (long) Math.pow(10, digitCount) - 1;
        
        do {
            uniqueNumber = min + random.nextInt((int) (max - min + 1));
        } while (this.individualCustomerRepository.existsByCustomerNo(Long.toString(uniqueNumber)));        
        return uniqueNumber;
    }

	@Override
	public boolean checkIfIdentificationNumberCustomer(IndividualCustomerRequest customerRequest) {
		return individualCustomerRepository.existsByIdentificationNumber(customerRequest.getIdentificationNumber());

	}

	@Override
	public ResponseEntity<DataResult<List<IndividualCustomer>>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(
				new SuccesDataResult<List<IndividualCustomer>>(this.individualCustomerRepository.findByIsActiveTrue()));
	}

	@Override
	public ResponseEntity<DataResult<IndividualCustomer>> getByIdentificationNumber(String identificationNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<IndividualCustomer>(
				this.individualCustomerRepository.getByIdentificationNumber(identificationNumber)));

	}
	
	@Override
	public ResponseEntity<DataResult<IndividualCustomer>> getByCustomerNubmer(String customerNo){
		return ResponseEntity.status(HttpStatus.OK)
				.body(new SuccesDataResult<IndividualCustomer>(this.individualCustomerRepository.getByCustomerNo(customerNo)));
	}

	@Override
	public ResponseEntity<Result> updateIndividualCustomer(Integer id, IndividualCustomerRequest customerRequest) {
		IndividualCustomer customer = this.individualCustomerRepository.findById(id).orElseThrow(() -> new RuntimeException("kullanıcı bulunamadı"));
		//reflection utilse objeleri merge eden kod yazmak lazım(bne irfan ama vakit kalmadı :))))
		//beni backendden kovdukları için ui yazdım ve ui - backend entegrasyonları yaptım :((((
		customer.setCustomerFirstName(customerRequest.getCustomerFirstName());
		customer.setCustomerLastname(customerRequest.getCustomerLastname());
		customer.setCustomerDateOfBrith(customerRequest.getCustomerDateOfBrith());
		customer.setIdentificationNumber(customerRequest.getIdentificationNumber());
		customer.setCustomerBusinessNumber(customerRequest.getCustomerBusinessNumber());
		customer.setCustomerPhoneNumber(customerRequest.getCustomerPhoneNumber());
		customer.setEmail(customerRequest.getEmail());
		customer.setAddress(customerRequest.getAddress());
		customer.setActive(customerRequest.isActive());
		IndividualCustomer savedCustumer = this.individualCustomerRepository.save(customer);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesResult("Kullanıcı Başarıyla Güncellendi.."));
	}

	@Override
	public ResponseEntity<Result> addCash(@RequestBody String identityNumber, double amount) {
		IndividualCustomer customer = individualCustomerRepository.getByIdentificationNumber(identityNumber);
		Account account = accountRepository.getByCustomerId(customer.getId());
		if (account == null)
			ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResult("Müşteriye Ait Bir Banka Hesabı Bulunamadı..."));
		double currentBalance = account.getTotalMoney() + amount;
		account.setTotalMoney(currentBalance);
		Account savedAccount = this.accountRepository.save(account);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesResult("Hesap Bakiyesi Başarıyla Güncellendi.."));
	}

	@Override
	public ResponseEntity<Result> cashTransfer(String identityNumber, double amount) {
		IndividualCustomer customer = individualCustomerRepository.getByIdentificationNumber(identityNumber);
		Account account = accountRepository.getByCustomerId(customer.getId());
		if (account == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResult("Müşteriye Ait Bir Banka Hesabı Bulunamadı..."));
		} else if (account.getTotalMoney() < amount) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResult("Bu işlem için bakiyeniz yetersiz."));
		} else {
			double currentBalance = account.getTotalMoney() - amount;
			account.setTotalMoney(currentBalance);
			accountRepository.save(account);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SuccesResult("Hesap Bakiyesi Başarıyla Güncellendi.."));
		}
	}

	@Override
	public ResponseEntity<Result> setActive(String idenTificationNumber) {
		IndividualCustomer customer = this.individualCustomerRepository.getByIdentificationNumber(idenTificationNumber);
		if(customer == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Kimlik Numarasına Ait Kullanıcı Bulunamadı..")); 
		customer.setActive(false);
		IndividualCustomer savedCustomer = this.individualCustomerRepository.save(customer);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesResult("Kullanıcı Başarıyla Silindi.."));
	}

}
