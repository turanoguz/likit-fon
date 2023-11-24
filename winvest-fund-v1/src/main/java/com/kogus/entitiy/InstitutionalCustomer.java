package com.kogus.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.kogus.auth.entity.User;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "institutional_customers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InstitutionalCustomer extends Customer {


	@Column(name = "customer_tax_no", unique = true)	
    private String customerTaxNo;
    
    @Column(name = "company_name")
    private String companyName;
    
    @Column(name = "customer_phone")
    private String customerPhone;
    
    
    
    
    
}
