package com.nik.hpm.application.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.hpm.enumcode.AppliRegDateType;
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
    private LocalDate regDate;
    
//    private List<ApplicationLog> applicationLogList;
    
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
    
}
