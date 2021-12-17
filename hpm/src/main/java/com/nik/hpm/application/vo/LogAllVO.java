package com.nik.hpm.application.vo;

import java.math.BigInteger;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LogAllVO {
	
	public LogAllVO(
			BigInteger id,
			String regNumber,
			String middleCategory,
			String reportNumber,
			String requestCustomerName,
			String customerName,
			String deviceName,
			String productionCompany,
			String model,
			String deviceNumber,
			String standard,
			String resolution,
			String unit,
			Date correctionDate,
			String place,
			String practitioner,
			String smallCategory,
			Date publishedDate,
			String technicalManager,
//			String inspectionType,
//			String carryType,
			BigInteger correctionFee,
			Integer quantity,
			String etc,
			String customerPicName,
			String customerMobile,
			String customerTel,
			String customerFax,
			String customerEmail,
			String customerAddress,
			String customerAddressDetail,
			String customerRepName,
			String customerCompanyRegNumber,
			String customerBizCondition,
			String customerItem,
			Integer customerPostNumber,
			String paymentStateType,
			String customerBillPicName,
			String customerBillPicTel,
			String customerBillPicMail,
			String consignmentCompanyNm,
			Date appliRegDate
			){
		
		this.id = id;
		this.regNumber = regNumber;
		this.middleCategory = middleCategory;
		this.reportNumber = reportNumber;
		this. requestCustomerName = requestCustomerName;
		this.customerName = customerName;
		this.deviceName = deviceName;
		this.productionCompany = productionCompany;
		this.model = model;
		this.deviceNumber = deviceNumber;
		this.standard = standard;
		this.resolution = resolution;
		this.unit = unit;
		this.correctionDate = correctionDate;
		this.place = place;
		this.practitioner = practitioner;
		this.smallCategory = smallCategory;
		this.publishedDate = publishedDate;
		this.technicalManager = technicalManager;
//		if(inspectionType != null) {
//			this.inspectionType = InspectionType.valueOf(inspectionType);
//		}
//		
//		if(carryType != null) {
//			this.carryType = CarryType.valueOf(carryType);
//		}
		this.correctionFee = correctionFee;
		this.quantity = quantity;
		this.etc = etc;
		this.customerPicName = customerPicName;
		this.customerMobile = customerMobile;
		this.customerTel = customerTel;
		this.customerFax = customerFax;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerAddressDetail = customerAddressDetail;
		this.customerRepName = customerRepName;
		this.customerCompanyRegNumber = customerCompanyRegNumber;
		this.customerBizCondition = customerBizCondition;
		this.customerItem = customerItem;
		this.customerPostNumber = customerPostNumber;
		this.paymentStateType = paymentStateType;
		this.customerBillPicName = customerBillPicName;
		this.customerBillPicTel = customerBillPicTel;
		this.customerBillPicMail = customerBillPicMail;
		this.consignmentCompanyNm = consignmentCompanyNm;
		this.appliRegDate = appliRegDate;
		
	}

	
	private BigInteger id;
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
	private Date correctionDate;
	private String place;
	private String practitioner;
	private String smallCategory;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date publishedDate;
	private String technicalManager;
	private String consignmentCompanyNm;
//	private InspectionType inspectionType;
//	private CarryType carryType;
	private BigInteger correctionFee;
	private Integer quantity;
	private String etc;
	private String customerPicName;
	private String customerMobile;
	private String customerTel;
	private String customerFax;
	private String customerEmail;
	private String customerAddress;
	private String customerAddressDetail;
	private String customerRepName;
	private String customerCompanyRegNumber;
	private String customerBizCondition;
	private String customerItem;
	private Integer customerPostNumber;
	private String paymentStateType;
	private String customerBillPicName;
	private String customerBillPicTel;
	private String customerBillPicMail;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appliRegDate;
}
