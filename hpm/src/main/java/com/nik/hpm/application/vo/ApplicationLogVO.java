package com.nik.hpm.application.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.consignmentcompany.vo.ConsignmentCompanyVO;
import com.nik.hpm.enumcode.CarryType;
import com.nik.hpm.enumcode.InspectionType;
import com.nik.hpm.enumcode.PaymentStateType;
import com.nik.hpm.enumcode.Yn;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationLogVO {
	
	public ApplicationLogVO() {}
	
	public ApplicationLogVO(ApplicationLog applicationLog) {
		
		this.id = applicationLog.getId();
		this.consignmentCompany = (applicationLog.getConsignmentCompany() != null)?new ConsignmentCompanyVO(applicationLog.getConsignmentCompany()):null;
		this.consignmentCompanyId = (applicationLog.getConsignmentCompany() != null)? applicationLog.getConsignmentCompany().getId():null;
		this.consignmentCompanyNm = (applicationLog.getConsignmentCompany() != null)? applicationLog.getConsignmentCompany().getName():null;
		this.inspectionType = applicationLog.getInspectionType();
		this.inspectionTypeNm = (applicationLog.getInspectionType()!= null )?applicationLog.getInspectionType().getValue():null;
		this.regNumber = applicationLog.getRegNumber();
		this.deviceName = applicationLog.getDeviceName();
		this.deviceNumber = applicationLog.getDeviceNumber();
		this.productionCompany = applicationLog.getProductionCompany();
		this.model = applicationLog.getModel();
		this.standard = applicationLog.getStandard();
		this.resolution = applicationLog.getResolution();
		this.quantity = applicationLog.getQuantity();
		this.reportNumber = applicationLog.getReportNumber();
		this.unit = applicationLog.getUnit();
		this.etc = applicationLog.getEtc();
		this.carryType = applicationLog.getCarryType();
		this.carryTypeNm = (applicationLog.getCarryType()!= null)?applicationLog.getCarryType().getValue():null;
		this.correctionFee = applicationLog.getCorrectionFee();
		this.correctionDate = applicationLog.getCorrectionDate();
		this.place = applicationLog.getPlace();
		this.practitioner = applicationLog.getPractitioner();
		this.middleCategory = applicationLog.getMiddleCategory();
		this.smallCategory = applicationLog.getSmallCategory();
		this.publishedDate = applicationLog.getPublishedDate();
		this.paymentStateType = applicationLog.getPaymentStateType();
		this.paymentStateTypeNm = (applicationLog.getPaymentStateType()!= null)?applicationLog.getPaymentStateType().getValue():null;
		if(applicationLog.getApplication() != null) {
			this.applicationId = applicationLog.getApplication().getId();
			this.customerSameYn = applicationLog.getApplication().getCustomerSameYn();
			if(applicationLog.getApplication().getCustomer() != null) {
				this.customerName = applicationLog.getApplication().getCustomer().getName();
				this.customerAdress = applicationLog.getApplication().getCustomer().getAdress();
				this.customerAdressDetail = applicationLog.getApplication().getCustomer().getAdressDetail();
			}
			
			if(applicationLog.getApplication().getRequestCustomer() != null) {
				this.requestCustomerName = applicationLog.getApplication().getRequestCustomer().getName();
				this.requestCustomerAddress = applicationLog.getApplication().getRequestCustomer().getAdress();
				this.requestCustomerAdressDetail = applicationLog.getApplication().getRequestCustomer().getAdressDetail();
			}
		}
	}
	
	private long id;
	
	private ConsignmentCompanyVO consignmentCompany;
	
	private Long consignmentCompanyId;
	
	private String consignmentCompanyNm;
	
	/** 검수 타입 */
	private InspectionType inspectionType;
	
	private String inspectionTypeNm;
	
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
	private CarryType carryType;
	
	private String carryTypeNm;
	
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
	
	private PaymentStateType paymentStateType;
	
	private String paymentStateTypeNm;
	
	private String requestCustomerName;
	
	private String requestCustomerAddress;
	
	private String requestCustomerAdressDetail;
	
	private Yn customerSameYn;
	
	private String customerName;
	
	private String customerAdress;
	
	private String customerAdressDetail;
	
	private long applicationId;
}
