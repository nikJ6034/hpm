package com.nik.hpm.application.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationLogVO {
	
	private long id;
	
	private long consignmentCompany;
	
	/** 검수 타입 */
	private String inspectionType;
	
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
	private String carryType;
	
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
	private String reportLanguage;
	
	/** 수정 유무 */
	private boolean edite;
	
	/** 삭제 구분 */
	private boolean del;
}
