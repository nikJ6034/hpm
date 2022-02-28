package com.nik.hpm.issue.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.enumcode.PaymentStateType;
import com.nik.hpm.enumcode.ReportLanguage;
import com.nik.hpm.enumcode.Yn;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class IssueLog implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** 접수번호 */
	private String regNumber;
	
	/** 성적서 번호 */
	private String reportNumber;
	
	/** 업체 */
	@ManyToOne
	@NotNull
	private Customer customer;
	
	/** 의뢰업체 */
	@ManyToOne
	private Customer requestCustomer;
	
	/** 기기명 */
	private String deviceName;
	
	/** 제작회사 */
	private String productionCompany;
	
	/** 모델 */
	private String model;
	
	/** 기기번호 */
	private String deviceNumber;
	
	/** 규격 */
	private String standard;
	
	/** 분해능 */
	private String resolution;
	
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
	
	/** 중분류 */
	private String middleCategory;
	
	/** 소분류 */
	private String smallCategory;
	
	/** 발행일 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishedDate;
	
	/** 기술챔임자 */
	private String technicalManager;
	
	/** 성적서 영어 */
	@Enumerated(EnumType.STRING)
	private ReportLanguage reportLanguage;
	
	/** 교정료 */
	private long correctionFee;
	
	/** hpm구분 */
	private String hpmType;
	
	/** 입력일 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate regDt;
	
	/** 접수일 */
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate appliRegDate;
	
	/** 삭제 구분 */
	@Enumerated(EnumType.STRING)
	@Column 
	private Yn delYn = Yn.N;
	
	
	/** 수량 */
	private int quantity;
	
	/** 비고 */
	private String etc;
	
	/** 마진률 */
	private int marginRate;
	
	/** 결제상태 */
	@Column(columnDefinition = "varchar(10) default 'NONE'")
	@Enumerated(EnumType.STRING)
	private PaymentStateType paymentStateType;
	

}
