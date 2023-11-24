package com.kogus.service;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kogus.dto.CustomerFundRequest;
import com.kogus.dto.SellFundResponse;
import com.kogus.entitiy.Account;
import com.kogus.entitiy.CustomerFund;
import com.kogus.entitiy.Fund;
import com.kogus.repository.AccountRepository;
import com.kogus.repository.CustomerFundRepository;
import com.kogus.repository.FundRepository;
import com.kogus.result.ErrorResult;
import com.kogus.result.Result;
import com.kogus.result.SuccesDataResult;
import com.kogus.service.abstracts.CustomerFundService;
import com.kogus.utils.ReflectionUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerFundServiceImpl implements CustomerFundService{

	private final CustomerFundRepository customerFundRepository;
	private final AccountRepository accountRepository;
	private final FundRepository fundRepository;

	private boolean existsByFundId(Integer fundId) {
		return fundRepository.existsById(fundId);
	}

	private CustomerFund getByCustomerFundId(Integer fundId) {
		return customerFundRepository.getByFundId(fundId);
	}

	private Account getAccountByCustomerId(Integer fundId) {
		return accountRepository.getAccountByCustomerId(fundId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Result> buyFund(CustomerFundRequest fundRequest) {
		Account customerAccount = getAccountByCustomerId(fundRequest.getCustomerId());
		if(!existsByFundId(fundRequest.getFundId())){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Sistemde Kayıtlı Olmayan Fon..!"));
		}
		if(fundRequest.getFundPrice() > customerAccount.getTotalMoney()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Müşteri Bakiyesi Fon Almak İçin yetersiz.."));
		}
		if(customerFundRepository.existsByFundId(fundRequest.getFundId())) {
			
			Account account = this.accountRepository.getAccountByCustomerId(fundRequest.getCustomerId());
			Fund fund = this.fundRepository.findById(fundRequest.getFundId()).orElseThrow(()-> new RuntimeException("Fon Bulunamadı.!"));
			CustomerFund customerFund = this.customerFundRepository.getByFundId(fundRequest.getFundId());
			double percentageChange = ((fund.getFundPrice()-customerFund.getUnitPrice())/ customerFund.getUnitPrice())*100 ;
			double fundPrice = customerFund.getFundPrice() * (1 + (percentageChange/100));
			customerFund.setFundPrice(fundPrice + (fundRequest.getFundQuantity()*fund.getFundPrice()));
			customerFund.setUnitPrice(fund.getFundPrice());
			customerFund.setFundQuantity(customerFund.getFundQuantity() + fundRequest.getFundQuantity());
			account.setTotalMoney(account.getTotalMoney() - (fund.getFundPrice() * fundRequest.getFundQuantity()));
			Account savedAccount = this.accountRepository.save(account);
			CustomerFund savedCustomerFund = this.customerFundRepository.save(customerFund);
			return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<Double>(savedAccount.getTotalMoney(),"Fon Alım İşlemi Başarıyla Gerçekleşti.."));
			
		}else {
			double newTotalMoney = (customerAccount.getTotalMoney() - fundRequest.getFundPrice());
			customerAccount.setTotalMoney(newTotalMoney);
			Fund fund = this.fundRepository.getById(fundRequest.getFundId());
			CustomerFund customerFund = ReflectionUtils.cast(fundRequest, CustomerFund.class);
			customerFund.setUnitPrice(fund.getFundPrice());
			Account savedAccount =  this.accountRepository.save(customerAccount);
			CustomerFund savedCustomer = this.customerFundRepository.save(customerFund);
			return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<Double>(newTotalMoney ,"Fon Alım İşlemi Başarıyla Gerçekleşti.."));
		}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Result> sellFund(CustomerFundRequest fundRequest) {
		Account customerAccount = getAccountByCustomerId(fundRequest.getCustomerId());
		CustomerFund funds = customerFundRepository.getByFundId(fundRequest.getFundId());
		if(!existsByFundId(fundRequest.getFundId()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Sistemde Kayıtlı Olmayan Fon..!"));
		if(fundRequest.getFundQuantity() > funds.getFundQuantity()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult("Müşterinin Fonu Satmak İçin yetersiz.."));
		}
		if (customerFundRepository.existsByFundId(fundRequest.getFundId())) {
			CustomerFund customerFund = getByCustomerFundId(fundRequest.getFundId());
			Fund fund = this.fundRepository.findById(fundRequest.getFundId()).orElseThrow(()->  new RuntimeException("Fon Bulunamadı!"));
			double newTotalMoney = (fundRequest.getFundQuantity()*fund.getFundPrice());
			double newAccountMoney = newTotalMoney + customerAccount.getTotalMoney();
			int newFundQuantity = (customerFund.getFundQuantity() - fundRequest.getFundQuantity());
			customerFund.setFundPrice(customerFund.getFundPrice() - newTotalMoney);
			customerFund.setFundQuantity(newFundQuantity);
			customerAccount.setTotalMoney(newAccountMoney);
			Account savedAccount = this.accountRepository.save(customerAccount);
			CustomerFund savedCustomerFund = this.customerFundRepository.save(customerFund);
			SellFundResponse response = new SellFundResponse();
			response.setMoney(savedAccount.getTotalMoney());
			response.setQuantity(savedCustomerFund.getFundQuantity());
			response.setFundId(fund.getId());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SuccesDataResult<SellFundResponse>(response,"Fon Satım İşlemi Başarıyla Gerçekleşti.."));
		}else {
			double newTotalMoney = (customerAccount.getTotalMoney() + fundRequest.getFundPrice());
			customerAccount.setTotalMoney(newTotalMoney);
			CustomerFund customerFund = ReflectionUtils.cast(fundRequest, CustomerFund.class);
			Account savedAccount =  this.accountRepository.save(customerAccount);
			CustomerFund savedCustomer = this.customerFundRepository.save(customerFund);
			SellFundResponse response = new SellFundResponse();
			response.setMoney(savedAccount.getTotalMoney());
			response.setQuantity(savedCustomer.getFundQuantity());
			return ResponseEntity.status(HttpStatus.OK).body(new SuccesDataResult<SellFundResponse>(response ,"Fon Satım İşlemi Başarıyla Gerçekleşti.."));
		}
	}

}