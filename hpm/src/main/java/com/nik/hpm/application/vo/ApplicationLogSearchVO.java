package com.nik.hpm.application.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationLogSearchVO {
	
	private long id;
	
	private ConsignmentCompany consignmentCompany;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDt;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDt;
}
