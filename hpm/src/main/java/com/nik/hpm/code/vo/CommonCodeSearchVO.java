package com.nik.hpm.code.vo;

import com.nik.hpm.common.search.vo.CommonSearchVO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonCodeSearchVO extends CommonSearchVO{
	
	private String name;
	private String pCode;
	private String cCode;
}
