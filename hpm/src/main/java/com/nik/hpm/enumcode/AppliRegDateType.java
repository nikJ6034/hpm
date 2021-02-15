package com.nik.hpm.enumcode;

import lombok.Getter;

@Getter
public enum AppliRegDateType {

	VISIT("방문하신날짜"),
	DELIVERY("택배 도착일"),
	EXPORT("장비 반출일");
	
	private String name;
	private String value;
	
	AppliRegDateType(String name){
		this.name = name;
		this.value= this.toString();
	}
}
