package com.kogus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FundRequest {

	private String fundName;
	
	private String fundCode;
	
	private String fundType;
	
	private String fundFounder;

	private double fundPrice;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fundDate;
	
}
