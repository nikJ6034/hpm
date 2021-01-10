package com.nik.hpm.enumcode;

import lombok.Getter;

@Getter
public enum TakeOverType {

	DIRECTLY("직접전달"),
	VISIT("고객방문"),
	DELIVERY("택배");
	
	private String name;
	
	TakeOverType(String name){
		this.name = name;
	}
}
