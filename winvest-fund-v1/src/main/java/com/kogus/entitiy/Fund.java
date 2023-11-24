package com.kogus.entitiy;


import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "funds")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Fund {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Fon adı Boş Bırakılamaz..")
	@NotBlank(message = "Fon adı Boş Bırakılamaz..")
	@Column(name = "fund_name", unique = true)
	private String fundName;

	@NotNull(message = "Fon Kodu Boş Bırakılamaz..")
	@NotBlank(message = "Fon Kodu Boş Bırakılamaz..")
	@Size(max = 3, min = 3, message = "Fon Kodu 3 Karakter Olmalıdır..")
	@Column(name = "fund_code")
	private String fundCode;
	
	@NotNull(message = "Fon Tipi Boş Bırakılamaz..")
	@NotBlank(message = "Fon Tipi Boş Bırakılamaz..")
	@Column(name = "fund_type")
	private String fundType;

	@NotNull(message = "Fon Kurucusu Boş Bırakılamaz..")
	@NotBlank(message =  "Fon Kurucusu Boş Bırakılamaz..")
	@Column(name = "fund_founder")
	private String fundFounder;

	@Column(name = "fund_price")
	private double fundPrice;

	@Temporal(TemporalType.DATE)
	private Date fundDate;
	
}
