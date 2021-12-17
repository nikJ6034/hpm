package com.nik.hpm.enumcode;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TakeOverType {

	DIRECTLY("직접전달"),
	VISIT("고객방문"),
	DELIVERY("택배");
	
	private String value;
	
	TakeOverType(String value){
		this.value = value;
	}
	
	public String getKey() {
        return name();
    }
	
//	@JsonCreator
    public static TakeOverType fromJson(@JsonProperty("key") String name) {
        return valueOf(name);
    }
}
