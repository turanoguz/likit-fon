package com.kogus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kogus.entitiy.Customer;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class IndividualCustomerResponse {

	private Customer customer;
    private String identificationNumber;
    private String customerNumber;
    private String customerFirstName;
    private String customerLastname;
    private String customerDateOfBrith;
    private String customerPhoneNumber;


}
