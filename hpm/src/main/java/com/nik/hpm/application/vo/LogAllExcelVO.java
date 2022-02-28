package com.nik.hpm.application.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LogAllExcelVO {
	
	private long id;
	private String regNumber;
	private String middleCategory;
	private String reportNumber;
	private String requestCustomerName;
	private String customerName;
	private String deviceName;
	private String productionCompany;
	private String model;
	private String deviceNumber;
	private String standard;
	private String resolution;
	private String unit;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate correctionDate;
	private String place;
	private String practitioner;
	private String smallCategory;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishedDate;
	private String technicalManager;
	private long correctionFee;
	private Integer quantity;
	private String etc;
	private String customerPicName;
	private String customerMobile;
	private String customerTel;
	private String customerFax;
	private String customerEmail;
	private String customerAddress;
	private String customerAddressDetail;
	private String paymentStateType;
	private String customerBillPicName;
	private String customerBillPicTel;
	private String customerBillPicMail;
	private String customerRepName;
	private String customerCompanyRegNumber;
	private String customerBizCondition;
	private String customerItem;
	private String customerPostNumber;
	private String consignmentCompanyNm;
	
	private String requestCustomerPicName;
	private String requestCustomerMobile;
	private String requestCustomerTel;
	private String requestCustomerFax;
	private String requestCustomerEmail;
	private String requestCustomerAddress;
	private String requestCustomerAddressDetail;
	private String requestCustomerRepName;
	private String requestCustomerCompanyRegNumber;
	private String requestCustomerBizCondition;
	private String requestCustomerItem;
	private Integer requestCustomerPostNumber;
	private String requestCustomerBillPicName;
	private String requestCustomerBillPicTel;
	private String requestCustomerBillPicMail;
}
