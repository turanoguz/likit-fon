package com.kogus.dto;

import java.util.List;

import com.kogus.entitiy.CustomerFund;

import lombok.Data;

@Data
public class CustomerFundsDto {

	private int customerId;
	
	private String customerName;
	
	private double totalMoney;
	
	private double totalFundPrice;
	
	private List<CustomerFundListDto> funds;
}

