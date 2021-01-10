package com.nik.hpm.consignmentcompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.nik.hpm.enumcode.DelYn;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ConsignmentCompany {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	private String tel;
	
	@Column
	private String fax;
	
	@Column
	private Integer postNumber;
	
	@Column
	private String adress;
	
	@Column
	private String adressDetail;
	
	@Column
	private String etc;
	
	@Enumerated(EnumType.STRING)
	@Column DelYn delYn;
}
