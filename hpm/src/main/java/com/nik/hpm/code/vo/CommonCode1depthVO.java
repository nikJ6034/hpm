package com.nik.hpm.code.vo;

import com.nik.hpm.code.entity.CommonCode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonCode1depthVO {

	public CommonCode1depthVO() {};
	
	public CommonCode1depthVO(CommonCode commonCode){
		if(commonCode != null) {
			this.id = commonCode.getId();
			this.code = commonCode.getCode();
			this.codeName = commonCode.getCodeName();
			this.codeDesc = commonCode.getCodeDesc();
			this.edite = commonCode.isEdite();
			this.del = commonCode.isDel();
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

}
