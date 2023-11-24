package com.kogus.entitiy;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "individual_customers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IndividualCustomer extends Customer{

    
	
	@Column(name = "customer_first_name")
	private String customerFirstName;
	
	@Column(name = "customer_last_name")
	private String customerLastname;

    @Column(unique = true)
    private String identificationNumber;
    
    @Column(name = "cutomer_name")
    private String customerBusinessNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "customer_date_of_birth")
    private Date customerDateOfBrith;
    
    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;
    
    @Column(name = "Adress")
    private String address;
    
    
    
    
    
    
    
    

}

