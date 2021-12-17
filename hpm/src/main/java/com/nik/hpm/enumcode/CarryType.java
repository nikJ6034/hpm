package com.nik.hpm.enumcode;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CarryType {
	
	PARTICIPATION("출장"),
	EXPORT("반출"),
	IMPORT("입고");
	
	private String value;
	
	CarryType(String value){
		this.value = value;
	}
	
	public String getKey() {
        return name();
    }
	
//	@JsonCreator
    public static CarryType fromJson(@JsonProperty("key") String name) {
        return valueOf(name);
    }
}
