package com.nik.hpm.application.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nik.hpm.attachfile.entity.AttachFile;
import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.enumcode.AppliRegDateType;
import com.nik.hpm.enumcode.TakeOverType;
import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

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
	
	@Column
	private String requestCustomerName;
	
	@Column
	private String requestCustomerAddress;
	
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
	
	@OneToMany(mappedBy = "application", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<ApplicationLog> applicationLogList;
	
	@ManyToOne
	private AttachFile customerSignImg;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Yn delYn;
}
