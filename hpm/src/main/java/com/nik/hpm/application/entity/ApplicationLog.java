package com.nik.hpm.application.entity;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.hpm.code.ReportLanguage;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ApplicationLog {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@NotNull
	private Application application;
	
	@ManyToOne
	@NotNull
	private ConsignmentCompany consignmentCompany;
	
	/** 기기명 */
	private String deviceName;
	
	/** 수량 */
	private int quantity;
	
	/** 성적서 번호 */
	private String reportNumber;
	
	/** 제작 회사 */
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
