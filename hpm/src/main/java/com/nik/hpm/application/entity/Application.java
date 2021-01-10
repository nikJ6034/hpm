package com.nik.hpm.application.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.enumcode.AppliRegDateType;
import com.nik.hpm.enumcode.DelYn;
import com.nik.hpm.enumcode.TakeOverType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Application {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** 의뢰업체 */
	@ManyToOne
	private Customer requestCustomer;
	
	/** 업체 */
	@ManyToOne
	@NotNull
	private Customer customer;
	
	/** 현장 교정 필요 */
	@Enumerated(EnumType.STRING)
	@Column
	private DelYn fieldCorrectionNeedYn;
	
	/** 권장 교정일 주기 기입 여부 */
	@Enumerated(EnumType.STRING)
	private DelYn recCalibrationDayYn;
	
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
	private TakeOverType takeOverType;
	
	/** 접수자 */
	@Column
	private String regId;
	
	/** 접수일 */
	@Column
	private LocalDate regDate;
	
	@OneToMany(mappedBy = "application")
	private List<ApplicationLog> applicationLogList;
	
	@Enumerated(EnumType.STRING)
	@Column
	private DelYn delYn;
}
