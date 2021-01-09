package com.nik.hpm.code.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonCodeVO {
	
	private long id;
	
	/** 분류코드 */
	private String code;
	
	/** 분류코드 이름 */
	private String codeName;
	
	/** 분류코드 설명 */
	private String codeDesc;
	
	/** 수정 유무 */
	private boolean edite;
	
	/** 삭제 구분 */
	private boolean del;
	
	private long pid;
	
	private List<CommonCodeVO> codeList = new ArrayList<>();

}
