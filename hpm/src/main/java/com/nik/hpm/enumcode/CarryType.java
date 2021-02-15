package com.nik.hpm.enumcode;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CarryType {
	
	PARTICIPATION("출장"),
	EXPORT("반출"),
	IMPORT("입고");
	
	private String name;
	private String value;
	
	CarryType(String name){
		this.name = name;
		this.value= this.toString();
	}
}
