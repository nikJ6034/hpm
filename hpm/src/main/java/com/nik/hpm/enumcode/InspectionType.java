package com.nik.hpm.enumcode;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

/**
 * @author nikJ
 * 검수 타입
 */

@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum InspectionType {
	CORRECTION("교정"),
	TEST("시험"),
	SELF("자체");
	
	private String value;
	
	InspectionType(String value){
		this.value = value;
	}
	
    public String getKey() {
        return name();
    }
	
//	@JsonCreator
    public static InspectionType fromJson(@JsonProperty("key") String name) {
        return valueOf(name);
    }
}
