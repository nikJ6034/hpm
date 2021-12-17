package com.nik.hpm.enumcode;

import lombok.Getter;

@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaymentStateType {

	PAY("결재"),
	NONE("미결재");
	
	private String value;
	
	PaymentStateType(String value){
		this.value = value;
	}
	
	public String getKey() {
        return name();
    }
	
//	@JsonCreator
//    public static PaymentStateType fromJson(@JsonProperty("key") String name) {
//        return valueOf(name);
//    }
}
