package com.nik.hpm.issue.vo;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.enumcode.ReportLanguage;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IssueLogVO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** 접수번호 */
	private String regNumber;
	
	/** 성적서 번호 */
	private String reportNumber;
	
	private String requestCustomerName;
	
	private Customer customer;
	
	/** 장비명 */
	private String deviceName;
	
	/** 제조사 */
	private String productionCompany;
	
	/** 기기번호 */
	private String deviceNumber;
	
	/** 규격 */
	private String standard;
	
	/** 단위 */
	private String unit;
	
	/** 교정일자 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate correctionDate;
	
	/** 장소 */
	private String place;
	
	/** 실무자 */
	private String practitioner;
	
	/** 소분류 */
	private String smallCategory;
	
	/** 발행일 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishedDate;
	
	/** 기술챔임자 */
	private String technicalManager;
	
	/** 성적서 영어 */
	private ReportLanguage reportLanguage;
	
	/** 모델 */
	private String model;
	
	/** 분해능 */
	private String resolution;
	
	/** 수량 */
	private int quantity;
	
	/** 비고 */
	private String etc;
	
	/** 교정료 */
	private long correctionFee;
	
	/** 중분류 */
	private String middleCategory;
	
	/** 마진률 */
	private int marginRate;
	
	/** 결제상태 */
	private String paymentStateType;
	
	/** 입력일 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate regDt;
	
	/** Hpm 구분 */
	private String hpmType;
}
