package com.nik.hpm.application.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.nik.hpm.common.search.vo.CommonSearchVO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationSearchVO extends CommonSearchVO{
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private long customer;
	/** 검색 조건 */
	private String dateCondition;
}
