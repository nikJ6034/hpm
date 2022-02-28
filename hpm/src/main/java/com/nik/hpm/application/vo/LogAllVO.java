package com.nik.hpm.application.vo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogAllVO {

	public LogAllVO(
			Long id
			, String reg_number
			, String middle_category
			, String report_number
			, String request_customer_name
			, String customer_name
			, String device_name
			, String production_company
			, String model
			, String device_number
			, String standard
			, String resolution
			, String unit
			, Date correction_date
			, String place
			, String practitioner
			, String small_category
			, Date published_date
			, String technical_manager
			, Long correction_fee
			, Integer quantity
			, String etc
			, String customer_pic_name
			, String customer_mobile
			, String customer_tel
			, String customer_fax
			, String customer_email
			, String customer_address
			, String customer_address_detail
			, String customer_rep_name
			, String customer_company_reg_number
			, String customer_biz_condition
			, String customer_item
			, Integer customer_post_number
			, String payment_state_type
			, String customer_bill_pic_name
			, String customer_bill_pic_tel
			, String customer_bill_pic_mail
			, String consignment_company_name
			, String request_customer_pic_name
			, String request_customer_mobile
			, String request_customer_tel
			, String request_customer_fax
			, String request_customer_email
			, String request_customer_address
			, String request_customer_address_detail
			, String request_customer_rep_name
			, String request_customer_company_reg_number
			, String request_customer_biz_condition
			, String request_customer_item
			, Integer request_customer_post_number
			, String request_customer_bill_pic_name
			, String request_customer_bill_pic_tel
			, String request_customer_bill_pic_mail
			, Date appli_reg_date
		) 
	{

		this.id = id;
		this.regNumber = reg_number;
		this.middleCategory = middle_category;
		this.reportNumber = report_number;
		this.requestCustomerName = request_customer_name;
		this.customerName = customer_name;
		this.deviceName = device_name;
		this.productionCompany = production_company;
		this.model = model;
		this.deviceNumber = device_number;
		this.standard = standard;
		this.resolution = resolution;
		this.unit = unit;
		if (correction_date != null) {
			this.correctionDate = Instant.ofEpochMilli(correction_date.getTime()).atZone(ZoneId.systemDefault())
					.toLocalDate();
		}
		this.place = place;
		this.practitioner = practitioner;
		this.smallCategory = small_category;
		if (published_date != null) {
			this.publishedDate = Instant.ofEpochMilli(published_date.getTime()).atZone(ZoneId.systemDefault())
					.toLocalDate();

		}
		this.correctionFee = correction_fee;
		this.quantity = quantity;
		this.etc = etc;
		this.customerPicName = customer_pic_name;
		this.customerMobile = customer_mobile;
		this.customerTel = customer_tel;
		this.customerFax = customer_fax;
		this.customerEmail = customer_email;
		this.customerAddress = customer_address;
		this.customerAddressDetail = customer_address_detail;
		this.customerRepName = customer_rep_name;
		this.customerCompanyRegNumber = customer_company_reg_number;
		this.customerBizCondition = customer_biz_condition;
		this.customerItem = customer_item;
		this.customerPostNumber = customer_post_number;
		this.paymentStateType = payment_state_type;
		this.customerBillPicName = customer_bill_pic_name;
		this.customerBillPicTel = customer_bill_pic_tel;
		this.customerBillPicMail = customer_bill_pic_mail;
		this.consignmentCompanyNm = consignment_company_name;

		this.requestCustomerPicName = request_customer_pic_name;
		this.requestCustomerMobile = request_customer_mobile;
		this.requestCustomerTel = request_customer_tel;
		this.requestCustomerFax = request_customer_fax;
		this.requestCustomerEmail = request_customer_email;
		this.requestCustomerAddress = request_customer_address;
		this.requestCustomerAddressDetail = request_customer_address_detail;
		this.requestCustomerRepName = request_customer_rep_name;
		this.requestCustomerCompanyRegNumber = request_customer_company_reg_number;
		this.requestCustomerBizCondition = request_customer_biz_condition;
		this.requestCustomerItem = request_customer_item;
		this.requestCustomerPostNumber = request_customer_post_number;
		this.requestCustomerBillPicName = request_customer_bill_pic_name;
		this.requestCustomerBillPicTel = request_customer_bill_pic_tel;
		this.requestCustomerBillPicMail = request_customer_bill_pic_mail;
		this.appliRegDate = Instant.ofEpochMilli(appli_reg_date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	private Long id;
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
	private String consignmentCompanyNm;
	private Long correctionFee;
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
	private LocalDate appliRegDate;
}
