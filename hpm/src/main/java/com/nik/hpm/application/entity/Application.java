package com.nik.hpm.application.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.nik.hpm.code.DelYn;
import com.nik.hpm.customer.enitity.Customer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Application {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** 의뢰업체 */
	@ManyToOne
	private Customer requestCustomer;
	
	/** 업체 */
	@ManyToOne
	@NotNull
	private Customer customer;
	
	@OneToMany(mappedBy = "application")
	private List<ApplicationLog> applicationLogList;
	
	@Enumerated(EnumType.STRING)
	@Column DelYn delYn;
}
