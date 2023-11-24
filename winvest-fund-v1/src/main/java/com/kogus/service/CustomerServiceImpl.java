package com.kogus.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kogus.dto.AccountsDto;
import com.kogus.dto.CashRequest;
import com.kogus.dto.CustomerDto;
import com.kogus.dto.CustomerFundListDto;
import com.kogus.dto.CustomerFundsDto;
import com.kogus.entitiy.Account;
import com.kogus.entitiy.Customer;
import com.kogus.entitiy.CustomerFund;
import com.kogus.entitiy.Fund;
import com.kogus.entitiy.IndividualCustomer;
import com.kogus.entitiy.InstitutionalCustomer;
import com.kogus.repository.AccountRepository;
import com.kogus.repository.CustomerFundRepository;
import com.kogus.repository.CustomerRespository;
import com.kogus.repository.FundRepository;
import com.kogus.repository.IndividualCustomerRepository;
import com.kogus.repository.InstitutionalCustomerRepository;
import com.kogus.result.DataResult;
import com.kogus.result.ErrorDataResult;
import com.kogus.result.ErrorResult;
import com.kogus.result.Result;
import com.kogus.result.SuccesDataResult;
import com.kogus.service.abstracts.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRespository customerRespository;
	private final IndividualCustomerRepository individualCustomerRepository;
	private final InstitutionalCustomerRepository institutionalCustomerRepository;
	private final AccountRepository accountRepository;
	private final CustomerFundRepository customerFundRepository;
	private final FundRepository fundRepository;
	

	@Override
	public ResponseEntity<DataResult<List<Customer>>> getAll() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new SuccesDataResult<List<Customer>>(this.customerRespository.findAll()));
	}

	@Override
	public ResponseEntity<DataResult<List<CustomerDto>>> getCustomerAccounts(String number) {
		List<CustomerDto> customerDtoGlobal = new ArrayList<>();
		if (this.individualCustomerRepository.existsByIdentificationNumber(number)) {
			CustomerDto customers = new CustomerDto();
			IndividualCustomer icustomer = individualCustomerRepository.getByIdentificationNumber(number);
			customers.setId(icustomer.getId());
			customers.setName(icustomer.getCustomerFirstName()+" "+ icustomer.getCustomerLastname());
			List<Account> accounts = this.accountRepository.findAccountByCustomerId(icustomer.getId());
			List<AccountsDto> accountsDtos = new ArrayList<>();
			for (Account a : accounts) {
				AccountsDto accountsDto = new AccountsDto();
				accountsDto.setCustomerId(a.getCustomerId());
				accountsDto.setAccountName(a.getAccountName());
				accountsDto.setTotalMoney(a.getTotalMoney());
				accountsDtos.add(accountsDto);
			}
			customers.setAccounts(accountsDtos);
			customerDtoGlobal.add(customers);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SuccesDataResult<List<CustomerDto>>(customerDtoGlobal, "Veri Başarıyla Listelendi.."));

		} else if (this.institutionalCustomerRepository.existsByCustomerTaxNo(number)) {
			CustomerDto customersDto = new CustomerDto();
			InstitutionalCustomer customer = institutionalCustomerRepository.getByCustomerTaxNo(number);
			customersDto.setId(customer.getId());
			customersDto.setName(customer.getCompanyName());
			List<Account> accounts = this.accountRepository.findAccountByCustomerId(customer.getId());
			List<AccountsDto> accountsList = new ArrayList<>();
			for (Account a : accounts) {
				AccountsDto accountsDto = new AccountsDto();
				accountsDto.setCustomerId(a.getCustomerId());
				accountsDto.setAccountName(a.getAccountName());
				accountsDto.setTotalMoney(a.getTotalMoney());
				accountsList.add(accountsDto);
			}
			customersDto.setAccounts(accountsList);
			customerDtoGlobal.add(customersDto);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SuccesDataResult<List<CustomerDto>>(customerDtoGlobal, "Veri Başarıyla Listelendi.."));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDataResult<List<CustomerDto>>(
					"Girilen Müşteri Numarası Veya Kimlik Numarası Sistemde Kayıtlı Değildir.."));
		}
	}
	
	@Override
	public ResponseEntity<DataResult<List<CustomerDto>>> getCustomerAccountsByCustomerNo(String number){
		List<CustomerDto> customerDtoGlobal = new ArrayList<>();
		if (this.individualCustomerRepository.existsByCustomerNo(number)) {
			CustomerDto customers = new CustomerDto();
			IndividualCustomer icustomer = individualCustomerRepository.getByCustomerNo(number);
			customers.setId(icustomer.getId());
			customers.setName(icustomer.getCustomerFirstName()+" "+ icustomer.getCustomerLastname());
			List<Account> accounts = this.accountRepository.findAccountByCustomerId(icustomer.getId());
			List<AccountsDto> accountsDtos = new ArrayList<>();
			for (Account a : accounts) {
				AccountsDto accountsDto = new AccountsDto();
				accountsDto.setCustomerId(a.getCustomerId());
				accountsDto.setAccountName(a.getAccountName());
				accountsDto.setTotalMoney(a.getTotalMoney());
				accountsDtos.add(accountsDto);
			}
			customers.setAccounts(accountsDtos);
			customerDtoGlobal.add(customers);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SuccesDataResult<List<CustomerDto>>(customerDtoGlobal, "Veri Başarıyla Listelendi.."));

		} else if (this.institutionalCustomerRepository.existsByCustomerNo(number)) {
			CustomerDto customersDto = new CustomerDto();
			InstitutionalCustomer customer = institutionalCustomerRepository.getByCustomerNo(number);
			customersDto.setId(customer.getId());
			customersDto.setName(customer.getCompanyName());
			List<Account> accounts = this.accountRepository.findAccountByCustomerId(customer.getId());
			List<AccountsDto> accountsList = new ArrayList<>();
			for (Account a : accounts) {
				AccountsDto accountsDto = new AccountsDto();
				accountsDto.setCustomerId(a.getCustomerId());
				accountsDto.setAccountName(a.getAccountName());
				accountsDto.setTotalMoney(a.getTotalMoney());
				accountsList.add(accountsDto);
			}
			customersDto.setAccounts(accountsList);
			customerDtoGlobal.add(customersDto);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SuccesDataResult<List<CustomerDto>>(customerDtoGlobal, "Veri Başarıyla Listelendi.."));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDataResult<List<CustomerDto>>(
					"Girilen Müşteri Numarası Veya Kimlik Numarası Sistemde Kayıtlı Değildir.."));
		}
		
	}

	@Override
	public ResponseEntity<DataResult<List<CustomerFundsDto>>> getCustomerFund(String number) {
		double fundTotalPrice = 0;
		List<CustomerFundsDto> customerFundsGlobalDto = new ArrayList<>();
		if (this.individualCustomerRepository.existsByIdentificationNumber(number)) {
			CustomerFundsDto customerDto = new CustomerFundsDto();
			IndividualCustomer customer = this.individualCustomerRepository.getByIdentificationNumber(number);
			Account account = this.accountRepository.getAccountByCustomerId(customer.getId());
			List<CustomerFund> customerFunds = this.customerFundRepository.findByCustomerId(customer.getId());
			customerDto.setCustomerId(customer.getId());
			customerDto.setCustomerName(customer.getCustomerFirstName() +" "+ customer.getCustomerLastname());
			customerDto.setTotalMoney(account.getTotalMoney());
			List<CustomerFundListDto> customerFundList = new ArrayList<>();
			for(CustomerFund c : customerFunds) {
				Fund fund = this.fundRepository.getById(c.getFundId());
				CustomerFundListDto customerFundListDto = new CustomerFundListDto();
				customerFundListDto.setFundId(c.getFundId());
				customerFundListDto.setFundCode(fund.getFundCode());
				customerFundListDto.setFundname(fund.getFundName());
				customerFundListDto.setFundPrice(c.getFundPrice());
				customerFundListDto.setFundQuantity(c.getFundQuantity());
				customerFundListDto.setUnitPrice(c.getUnitPrice());
				fundTotalPrice += c.getFundPrice(); 
				customerFundList.add(customerFundListDto);
			}
			customerDto.setTotalFundPrice(fundTotalPrice);
			customerDto.setFunds(customerFundList);
			customerFundsGlobalDto.add(customerDto);
			return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<List<CustomerFundsDto>>(
					customerFundsGlobalDto, "Veri Başarıyla Listelendi.."));
		} else if (this.institutionalCustomerRepository.existsByCustomerTaxNo(number)) {
			CustomerFundsDto customerFundsDto = new CustomerFundsDto();
			InstitutionalCustomer customer = this.institutionalCustomerRepository.getByCustomerTaxNo(number);
			Account account = this.accountRepository.getAccountByCustomerId(customer.getId());
			List<CustomerFund> customerFunds = this.customerFundRepository.findByCustomerId(customer.getId());
			customerFundsDto.setCustomerId(customer.getId());
			customerFundsDto.setCustomerName(customer.getCompanyName());
			customerFundsDto.setTotalMoney(account.getTotalMoney());
			List<CustomerFundListDto> customerFundList = new ArrayList<>();
			for(CustomerFund c : customerFunds ) {
				Fund fund = this.fundRepository.getById(c.getFundId());
				CustomerFundListDto customerFundListDto = new CustomerFundListDto();
				customerFundListDto.setFundId(c.getFundId());
				customerFundListDto.setFundname(fund.getFundName());
				customerFundListDto.setFundCode(fund.getFundCode());
				customerFundListDto.setFundPrice(c.getFundPrice());
				customerFundListDto.setFundQuantity(c.getFundQuantity());
				customerFundListDto.setUnitPrice(fund.getFundPrice());
				fundTotalPrice += c.getFundPrice();
				customerFundList.add(customerFundListDto);
			}
			customerFundsDto.setTotalFundPrice(fundTotalPrice);
			customerFundsDto.setFunds(customerFundList);
			customerFundsGlobalDto.add(customerFundsDto);
			return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<List<CustomerFundsDto>>(
					customerFundsGlobalDto, "Veri Başarıyla Listelendi.."));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDataResult<List<CustomerFundsDto>>(
					"Girilen Müşteri Numarası Veya Kimlik Numarası Sistemde Kayıtlı Değildir.."));
		}

	}

	@Override
	public ResponseEntity<DataResult<List<CustomerFundsDto>>> getCustomerFundByCustomerNo(String number) {
		double fundTotalPrice = 0;
		List<CustomerFundsDto> customerFundsGlobalDto = new ArrayList<>();
		if (individualCustomerRepository.existsByCustomerNo(number)) {
			CustomerFundsDto customerDto = new CustomerFundsDto();
			IndividualCustomer customer = this.individualCustomerRepository.getByCustomerNo(number);
			Account account = this.accountRepository.getAccountByCustomerId(customer.getId());
			List<CustomerFund> customerFunds = this.customerFundRepository.findByCustomerId(customer.getId());
			customerDto.setCustomerId(customer.getId());
			customerDto.setCustomerName(customer.getCustomerFirstName() +" "+ customer.getCustomerLastname());
			customerDto.setTotalMoney(account.getTotalMoney());
			List<CustomerFundListDto> customerFundList = new ArrayList<>();
			for(CustomerFund c : customerFunds ) {
				Fund fund = this.fundRepository.getById(c.getFundId());
				CustomerFundListDto customerFundListDto = new CustomerFundListDto();
				customerFundListDto.setFundId(c.getFundId());
				customerFundListDto.setFundname(fund.getFundName());
				customerFundListDto.setFundCode(fund.getFundCode());
				customerFundListDto.setFundPrice(c.getFundPrice());
				customerFundListDto.setFundQuantity(c.getFundQuantity());
				customerFundListDto.setUnitPrice(fund.getFundPrice());
				fundTotalPrice+=c.getFundPrice();
				customerFundList.add(customerFundListDto);
			}
			customerDto.setTotalFundPrice(fundTotalPrice);
			customerDto.setFunds(customerFundList);
			customerFundsGlobalDto.add(customerDto);
			return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<List<CustomerFundsDto>>(
					customerFundsGlobalDto, "Veri Başarıyla Listelendi.."));
		} else if (this.institutionalCustomerRepository.existsByCustomerNo(number)) {
			CustomerFundsDto customerFundsDto = new CustomerFundsDto();
			InstitutionalCustomer customer = this.institutionalCustomerRepository.getByCustomerNo(number);
			Account account = this.accountRepository.getAccountByCustomerId(customer.getId());
			List<CustomerFund> customerFunds = this.customerFundRepository.findByCustomerId(customer.getId());
			customerFundsDto.setCustomerId(customer.getId());
			customerFundsDto.setCustomerName(customer.getCompanyName());
			customerFundsDto.setTotalMoney(account.getTotalMoney());
			List<CustomerFundListDto> customerFundList = new ArrayList<>();
			for(CustomerFund c : customerFunds ) {
				Fund fund = this.fundRepository.getById(c.getFundId());
				CustomerFundListDto customerFundListDto = new CustomerFundListDto();
				customerFundListDto.setFundId(c.getFundId());
				customerFundListDto.setFundname(fund.getFundName());
				customerFundListDto.setFundCode(fund.getFundCode());
				customerFundListDto.setFundPrice(c.getFundPrice());
				customerFundListDto.setFundQuantity(c.getFundQuantity());
				customerFundListDto.setUnitPrice(fund.getFundPrice());
				fundTotalPrice+= c.getFundPrice();
				customerFundList.add(customerFundListDto);
			}
			
			customerFundsDto.setTotalFundPrice(fundTotalPrice);
			customerFundsDto.setFunds(customerFundList);
			customerFundsGlobalDto.add(customerFundsDto);
			return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<List<CustomerFundsDto>>(
					customerFundsGlobalDto, "Veri Başarıyla Listelendi.."));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDataResult<List<CustomerFundsDto>>(
					"Girilen Müşteri Numarası Sistemde Kayıtlı Değildir.."));
		}
	}
	
	@Override
	public ResponseEntity<Result> addCash(CashRequest addCashRequest){
		if(addCashRequest.getAmount()<= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Müşeteri hesabına minumum 1 tl yatırabilirsiniz.."));
		}
		Account account = this.accountRepository.getAccountByCustomerId(addCashRequest.getId());
		double newTotalMoney = ( account.getTotalMoney() + addCashRequest.getAmount());
		account.setTotalMoney(newTotalMoney);
		this.accountRepository.save(account);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<Double>(newTotalMoney,"Müşteri hesabına nakit eklendi.."));
	}

	@Override
	public ResponseEntity<Result> withdrawMoney(CashRequest request) {
		Account account = this.accountRepository.getAccountByCustomerId(request.getId());
		if(request.getAmount()>account.getTotalMoney()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Yetersiz bakiye!"));
		}
		double newTotalMoney = (account.getTotalMoney()-request.getAmount());
		account.setTotalMoney(newTotalMoney);
		this.accountRepository.save(account);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<Double>(newTotalMoney,"Para çekme İşlemi Başarılı"));
	}
}
