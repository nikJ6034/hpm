package com.nik.hpm.application.vo;

import com.nik.hpm.common.search.vo.CommonSearchVO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationSearchVO extends CommonSearchVO{
	
	private long customer;
}
