package com.nik.hpm.enumcode;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

/**
 * @author nikJ
 * 검수 타입
 */

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum InspectionType {
	CORRECTION("교정"),
	TEST("시험"),
	SELF("자체");
	
	private String name;
	private String value;
	
	InspectionType(String name){
		this.name = name;
		this.value= this.toString();
	}
}
