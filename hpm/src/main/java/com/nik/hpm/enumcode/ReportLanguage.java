package com.nik.hpm.enumcode;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReportLanguage implements Serializable{
	KO("국문"),
	EN("영문"),
	KOEN("국문, 영문");
	
	private String value;
	
	ReportLanguage(String value){
		this.value= value;
	}
	
	public String getKey() {
        return name();
    }
	
//	@JsonCreator
    public static ReportLanguage fromJson(@JsonProperty("key") String name) {
        return valueOf(name);
    }
	
	public static ReportLanguage findEnum(String value) {
		if("국문".equals(value)) {
			return ReportLanguage.KO;
		}else if("영문".equals(value)) {
			return ReportLanguage.EN;
		}else if("국문, 영문".equals(value)) {
			return ReportLanguage.KOEN;
		}else if("국문,영문".equals(value)) {
			return ReportLanguage.KOEN;
		}else {
			return null;
		}
	}
}
