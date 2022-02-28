package com.nik.hpm.application.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.attachfile.entity.AttachFile;
import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.enumcode.AppliRegDateType;
import com.nik.hpm.enumcode.ReportLanguage;
import com.nik.hpm.enumcode.TakeOverType;
import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationVO {
	
	public ApplicationVO() {
		
	}
	
	public ApplicationVO(Application application) {
		this.id = application.getId();
		this.customer = application.getCustomer();
		this.requestCustomer = application.getRequestCustomer();
		this.customerSameYn = application.getCustomerSameYn();
		this.fieldCorrectionNeedYn = application.getFieldCorrectionNeedYn();
		this.recCalibrationDayYn = application.getRecCalibrationDayYn();
		this.appliRegDateType = application.getAppliRegDateType();
		this.appliRegDateTypeNm = (application.getAppliRegDateType() != null)?application.getAppliRegDateType().getName():null;
		this.appliRegDate = application.getAppliRegDate();
		this.applicant = application.getApplicant();
		this.applicantTel = application.getApplicantTel();
		this.applicantEmail = application.getApplicantEmail();
		this.consignee = application.getConsignee();
		this.takeOverDate = application.getTakeOverDate();
		this.takeOverType = application.getTakeOverType();
		this.takeOverTypeNm = (application.getTakeOverType() != null)?application.getTakeOverType().getValue():null;
		this.regMember = application.getRegMember();
		this.regDate = application.getRegDate();
		if(application.getApplicationLogList() != null && !application.getApplicationLogList().isEmpty()) {
			this.applicationLogList = application.getApplicationLogList().stream().map(ApplicationLogVO::new).collect(Collectors.toList());
		}
		this.customerSignImg = application.getCustomerSignImg();
		this.reportLanguage = application.getReportLanguage();
		this.reportLanguageNm = (application.getReportLanguage() != null)?application.getReportLanguage().getValue():null;
		this.technicalManager = application.getTechnicalManager();
		this.deliveryInfo = application.getDeliveryInfo();
		this.regNm = application.getRegNm();
	}

	private long id;
	
	private Customer customer;
	
	private Customer requestCustomer;
	
	/** 발행업체 동일 여부 */
	private Yn customerSameYn;
	
	/** 현장 교정 필요 */
	private Yn fieldCorrectionNeedYn;
	
	/** 권장 교정일 주기 기입 여부 */
	private Yn recCalibrationDayYn;
	
	/** 교정신청 날짜 타입 */
	private AppliRegDateType appliRegDateType;
	
	private String appliRegDateTypeNm;
	
	/** 교정신청일 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate appliRegDate;
	
	/** 신청인 */
	private String applicant;
	
	/** 신청인 전화번호 */
	private String applicantTel;
	
	/** 신청인 이메일 */
	private String applicantEmail;
	
	/** 인수자 */
	private String consignee;
	
	/** 인수일 */
	private LocalDate takeOverDate;	
	
	/** 전달방법 */
	private TakeOverType takeOverType;
	
	private String takeOverTypeNm;
	
	/** 접수자 */
	private Member regMember;
	
	/** 접수일 */
	private LocalDate regDate;
	
	private List<ApplicationLogVO> applicationLogList = new ArrayList<>();
	
	private AttachFile customerSignImg;
	
	private ReportLanguage reportLanguage;
	
	private String reportLanguageNm;
	
	/** 기술챔임자 */
	private String technicalManager;
	
	private String deliveryInfo;
	
	private String regNm;
	
}
