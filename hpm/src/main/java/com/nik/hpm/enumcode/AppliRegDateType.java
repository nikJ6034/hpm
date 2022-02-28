package com.nik.hpm.enumcode;

import lombok.Getter;

@Getter
public enum AppliRegDateType {

	VISIT("방문"),
	DELIVERY("택배"),
	EXPORT("반출"),
	SPOTEXPORT("현장+반출");
	
	private String name;
	private String value;
	
	AppliRegDateType(String name){
		this.name = name;
		this.value= this.toString();
	}
}
