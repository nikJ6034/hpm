package com.nik.hpm.application.vo;

import java.time.LocalDate;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.hpm.enumcode.AppliRegDateType;
import com.nik.hpm.enumcode.CarryType;
import com.nik.hpm.enumcode.InspectionType;
import com.nik.hpm.enumcode.PaymentStateType;
import com.nik.hpm.enumcode.ReportLanguage;
import com.nik.hpm.enumcode.TakeOverType;
import com.nik.hpm.enumcode.Yn;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppLogAllListVO {

    private long id;
    
    /** 업체 */
    private String customerName;
    
    /** 담당자 */
    private String customerPicName;
    
    /** 전화번호 */
    private String customerTel;
    
    /** 연락처 */
    private String customerMobile;
    
    /** 팩스번호 */
    private String customerFax;
    
    /** 이메일 */
    private String customerEmail;
    
    /** 주소 */
    private String customerAddress;
    
    /** 주소 상세 */
    private String customerAddressDetail;
    
    private String requestCustomerName;
    
    private String requestCustomerAddress;
    
    /** 발행업체 동일 여부 */
    private Yn customerSameYn;
    
    /** 현장 교정 필요 */
    private Yn fieldCorrectionNeedYn;
    
    /** 권장 교정일 주기 기입 여부 */
    private Yn recCalibrationDayYn;
    
    /** 교정신청 날짜 타입 */
    private AppliRegDateType appliRegDateType;
    
    /** 교정신청일 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appliRegDate;
    
    /** 신청인 */
    private String applicant;
    
    /** 신청인 이메일 */
    private String applicantEmail;
    
    /** 인수자 */
    private String consignee;
    
    /** 인수일 */
    private LocalDate takeOverDate; 
    
    /** 전달방법 */
    private TakeOverType takeOverType;
    
    /** 접수자 */
    private String regMemberName;
    
    /** 접수일 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;
    
    private String consignmentCompanyName;
    
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
    
    private long correctionFee;
    
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
    private ReportLanguage reportLanguage;
    
    /** 접수번호 */
	private String regNumber;
	
	/** 모델 */
	private String model;
	
	/** 분해능 */
	private String resolution;
	
	/** 비고 */
	private String etc;
	
	private Long consignmentCompanyId;
	
	private String deliveryInfo;
	
	private InspectionType inspectionType;
	
	private CarryType carryType;
	
	private PaymentStateType paymentStateType;
	
	/** 결제담당자 이름 */
	@Column
	private String billPicName;
	/** 결제담당자 전화번호 */
	@Column
	private String billPicTel;
	/** 결제담당자 메일주소 */
	@Column
	private String billPicMail;
    
}
