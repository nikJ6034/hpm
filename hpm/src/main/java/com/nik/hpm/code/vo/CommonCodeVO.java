package com.nik.hpm.code.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nik.hpm.code.entity.CommonCode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonCodeVO {
	
	public CommonCodeVO() {};
	
	public CommonCodeVO(CommonCode commonCode){
		if(commonCode != null) {
			this.id = commonCode.getId();
			this.code = commonCode.getCode();
			this.codeName = commonCode.getCodeName();
			this.codeDesc = commonCode.getCodeDesc();
			this.edite = commonCode.isEdite();
			this.del = commonCode.isDel();
			this.codeList = commonCode.getCodeList().stream().map(CommonCode1depthVO::new).collect(Collectors.toList());
		}
	}
	
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
	
	private List<CommonCode1depthVO> codeList = new ArrayList<>();
	

}
