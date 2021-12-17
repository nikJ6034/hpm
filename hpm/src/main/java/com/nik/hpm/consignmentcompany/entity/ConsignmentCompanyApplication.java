package com.nik.hpm.consignmentcompany.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.hpm.application.vo.ApplicationLogVO;
import com.nik.hpm.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ConsignmentCompanyApplication implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private ConsignmentCompany consignmentCompany;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDt;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDt;
	
	/** 접수자 */
	@ManyToOne
	private Member regMember;
	
	@Transient
	private List<ApplicationLogVO> applicationLogList;

}
