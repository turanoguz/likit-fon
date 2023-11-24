package com.kogus.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerDto {
	
	private int id;
	private String name;
	private List<AccountsDto> accounts;

}
