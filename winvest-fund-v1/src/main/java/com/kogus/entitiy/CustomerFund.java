package com.kogus.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_funds")
public class CustomerFund {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "fund_id")
	private int fundId;
	
	@Column(name = "fund_quantity")
	private int fundQuantity;
	
	@Column(name = "fund_price")
	private double fundPrice;
	
	@Column(name = "unit_price")
	private double unitPrice;

}
