package com.kogus.dto;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data
public class IndividualCustomerRequest {
	
	
	private String customerFirstName;
	private String customerLastname;
	private String identificationNumber;
	private String email;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date customerDateOfBrith;
	private String customerPhoneNumber;
	private String customerBusinessNumber;
	private String address;
	@JsonFormat(pattern = "dd/MM/yyyy")
    private Date customerCreationDate;
    private boolean isActive;

}
