package com.kogus.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundResponse {

	private String fundName;

	private String fundCode;

	private String fundType;

	private String fundFounder;

	private String currencyType;

	private String riskValue;

	private String minimumAmount;

}
