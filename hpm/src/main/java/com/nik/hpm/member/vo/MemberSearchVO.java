package com.nik.hpm.member.vo;

import com.nik.hpm.common.search.vo.CommonSearchVO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberSearchVO extends CommonSearchVO{
	
	private String memberId;
	
	private String name;
}
