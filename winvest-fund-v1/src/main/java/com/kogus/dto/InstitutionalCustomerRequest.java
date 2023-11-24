package com.kogus.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kogus.entitiy.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InstitutionalCustomerRequest {

	private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date customerCreationDate;
	private String customerRepresentative;
    private String customerTaxNo;
    private String companyName;
    private String customerPhone;
    private boolean isActive;
    
    

}
