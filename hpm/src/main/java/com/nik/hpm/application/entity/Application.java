package com.nik.hpm.application.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nik.hpm.application.vo.LogAllVO;
import com.nik.hpm.attachfile.entity.AttachFile;
import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.enumcode.AppliRegDateType;
import com.nik.hpm.enumcode.ReportLanguage;
import com.nik.hpm.enumcode.TakeOverType;
import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

@SqlResultSetMapping(name = "LogAllVOMapping", classes = @ConstructorResult(targetClass = LogAllVO.class, columns = {
		@ColumnResult(name = "id", type = Long.class), 
		@ColumnResult(name = "reg_number", type = String.class),
		@ColumnResult(name = "middle_category", type = String.class), 
		@ColumnResult(name = "report_number", type = String.class),
		@ColumnResult(name = "request_customer_name", type = String.class),
		@ColumnResult(name = "customer_name", type = String.class), 
		@ColumnResult(name = "device_name", type = String.class),
		@ColumnResult(name = "production_company", type = String.class), 
		@ColumnResult(name = "model", type = String.class),
		@ColumnResult(name = "device_number", type = String.class),
		@ColumnResult(name = "standard", type = String.class), 
		@ColumnResult(name = "resolution", type = String.class),
		@ColumnResult(name = "unit", type = String.class), 
		@ColumnResult(name = "correction_date", type = Date.class),
		@ColumnResult(name = "place", type = String.class),
		@ColumnResult(name = "practitioner", type = String.class), 
		@ColumnResult(name = "small_category", type = String.class),
		@ColumnResult(name = "published_date", type = Date.class), 
		@ColumnResult(name = "technical_manager", type = String.class),
		@ColumnResult(name = "correction_fee", type = Long.class),
		@ColumnResult(name = "quantity", type = Integer.class), 
		@ColumnResult(name = "etc", type = String.class),
		@ColumnResult(name = "customer_pic_name", type = String.class), 
		@ColumnResult(name = "customer_mobile", type = String.class),
		@ColumnResult(name = "customer_tel", type = String.class),
		@ColumnResult(name = "customer_fax", type =String.class), 
		@ColumnResult(name = "customer_email", type = String.class),
		@ColumnResult(name = "customer_address", type = String.class), 
		@ColumnResult(name = "customer_address_detail", type = String.class),
		@ColumnResult(name = "customer_rep_name", type = String.class),
		@ColumnResult(name = "customer_company_reg_number", type = String.class), 
		@ColumnResult(name = "customer_biz_condition", type = String.class),
		@ColumnResult(name = "customer_item", type = String.class), 
		@ColumnResult(name = "customer_post_number", type = Integer.class),
		@ColumnResult(name = "payment_state_type", type = String.class),
		@ColumnResult(name = "customer_bill_pic_name", type = String.class), 
		@ColumnResult(name = "customer_bill_pic_tel", type = String.class),
		@ColumnResult(name = "customer_bill_pic_mail", type = String.class), 
		@ColumnResult(name = "consignment_company_name", type = String.class),
		@ColumnResult(name = "request_customer_pic_name", type = String.class),
		@ColumnResult(name = "request_customer_mobile", type = String.class), 
		@ColumnResult(name = "request_customer_tel", type = String.class),
		@ColumnResult(name = "request_customer_fax", type = String.class), 
		@ColumnResult(name = "request_customer_email", type = String.class),
		@ColumnResult(name = "request_customer_address", type = String.class),
		@ColumnResult(name = "request_customer_address_detail", type = String.class),
		@ColumnResult(name = "request_customer_rep_name", type = String.class), 
		@ColumnResult(name = "request_customer_company_reg_number", type = String.class),
		@ColumnResult(name = "request_customer_biz_condition", type = String.class), 
		@ColumnResult(name = "request_customer_item", type = String.class),
		@ColumnResult(name = "request_customer_post_number", type = Integer.class),
		@ColumnResult(name = "request_customer_bill_pic_name", type = String.class),
		@ColumnResult(name = "request_customer_bill_pic_tel", type = String.class), 
		@ColumnResult(name = "request_customer_bill_pic_mail", type = String.class),
		@ColumnResult(name = "appli_reg_date", type = Date.class),
	})
)

@Entity
@Getter @Setter
public class Application implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** 업체 */
	@ManyToOne
	@NotNull
	private Customer customer;
	
	/** 의뢰업체 */
	@ManyToOne
	private Customer requestCustomer;
	
	/** 발행업체 동일 여부 */
	@Enumerated(EnumType.STRING)
	@Column
	private Yn customerSameYn;
	
	/** 현장 교정 필요 */
	@Enumerated(EnumType.STRING)
	@Column
	private Yn fieldCorrectionNeedYn;
	
	/** 권장 교정일 주기 기입 여부 */
	@Enumerated(EnumType.STRING)
	private Yn recCalibrationDayYn;
	
	/** 성적서 영어 */
	@Enumerated(EnumType.STRING)
	private ReportLanguage reportLanguage;
	
	/** 교정신청 날짜 타입 */
	@Enumerated(EnumType.STRING)
	private AppliRegDateType appliRegDateType;
	
	/** 교정신청일 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate appliRegDate;
	
	/** 신청인 */
	@Column
	private String applicant;
	
	/** 신청인 전화번호 */
	@Column
	private String applicantTel;
	
	/** 신청인 이메일 */
	@Column
	private String applicantEmail;
	
	/** 인수자 */
	@Column
	private String consignee;
	
	/** 인수일 */
	@Column
	private LocalDate takeOverDate;	
	
	/** 전달방법 */
	@Enumerated(EnumType.STRING)
	private TakeOverType takeOverType;
	
	/** 접수자 */
	@ManyToOne
	private Member regMember;
	
	/** 접수일 */
	@Column
	private LocalDate regDate;
	
	@OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<ApplicationLog> applicationLogList;
	
//	@JsonIgnore
	@ManyToOne
	private AttachFile customerSignImg;
	
	/** 기술챔임자 */
	private String technicalManager;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Yn delYn;
	
	@Column
	private String deliveryInfo;
	
	/** 접수자 이름 */
	@Column
	private String regNm;
}
