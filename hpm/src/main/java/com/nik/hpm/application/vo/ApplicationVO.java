package com.nik.hpm.application.vo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.nik.hpm.attachfile.entity.AttachFile;
import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationVO {

	private long id;
	
	private Customer customer;
	
	private String requestCustomerName;
	
	private String requestCustomerAddress;
	
	/** 발행업체 동일 여부 */
	private Yn customerSameYn;
	
	/** 현장 교정 필요 */
	private Yn fieldCorrectionNeedYn;
	
	/** 권장 교정일 주기 기입 여부 */
	private Yn recCalibrationDayYn;
	
	/** 교정신청 날짜 타입 */
	private String appliRegDateType;
	
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
	private String takeOverType;
	
	/** 접수자 */
	private Member regMember;
	
	/** 접수일 */
	private LocalDate regDate;
	
	private List<ApplicationLogVO> applicationLogList;
	
	private AttachFile customerSignImg;
	
}
