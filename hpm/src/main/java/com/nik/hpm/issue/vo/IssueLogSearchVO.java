package com.nik.hpm.issue.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.nik.hpm.common.search.vo.CommonSearchVO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IssueLogSearchVO extends CommonSearchVO{

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	/** 검색 조건 */
	private String dateCondition;
	
	/** Hpm 구분 */
	private String hpmType;
}
