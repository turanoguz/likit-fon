package com.kogus.dto;


import lombok.Data;

@Data
public class CustomerFundRequest {

	private int customerId;
	
	private int fundId;
	
	private int fundQuantity;
	
	private double fundPrice;
}
