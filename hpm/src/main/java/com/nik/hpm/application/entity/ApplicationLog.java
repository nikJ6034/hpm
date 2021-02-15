package com.nik.hpm.application.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.enumcode.CarryType;
import com.nik.hpm.enumcode.InspectionType;
import com.nik.hpm.enumcode.ReportLanguage;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ApplicationLog implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@NotNull
	@JsonBackReference
	private Application application;
	
	
	/** 위탁기관 */
	@ManyToOne
	@NotNull
	private ConsignmentCompany consignmentCompany;
	
	/** 검수 타입 */
	@Enumerated(EnumType.STRING)
	private InspectionType inspectionType;
	
	/** 접수번호 */
	private String regNumber;
	
	/** 장비명 */
	private String deviceName;
	
	/** 기기번호 */
	private String deviceNumber;
	
	/** 제조사 */
	private String productionCompany;
	
	/** 모델 */
	private String model;
	
	/** 규격 */
	private String standard;
	
	/** 분해능 */
	private String resolution;
	
	/** 수량 */
	private int quantity;
	
	/** 성적서 번호 */
	private String reportNumber;
	
	/** 단위 */
	private String unit;
	
	/** 비고 */
	private String etc;
	
	/** 입출 타입 */
	@Enumerated(EnumType.STRING)
	private CarryType carryType;
	
	/** 교정료 */
	private Long correctionFee;
	
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
}
