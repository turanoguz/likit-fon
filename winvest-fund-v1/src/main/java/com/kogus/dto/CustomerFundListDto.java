package com.kogus.dto;

import lombok.Data;

@Data
public class CustomerFundListDto {
	private int fundId;
	private String fundname;
	private String fundCode;
	private int fundQuantity;
	private double fundPrice;
	private double unitPrice;
}
