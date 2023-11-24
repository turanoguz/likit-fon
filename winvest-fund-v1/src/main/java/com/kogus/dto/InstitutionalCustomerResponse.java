package com.kogus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kogus.entitiy.Customer;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class InstitutionalCustomerResponse {

	private String email;
	@Temporal(TemporalType.DATE)
    private Date customerCreationDate;
	private String customerRepresentative;
    private String customerTaxNo;
    private String customerNo;
    private String companyName;
    private String customerPhone;
    private String customerAddress;
    private boolean isActive;
    private String address;

}
