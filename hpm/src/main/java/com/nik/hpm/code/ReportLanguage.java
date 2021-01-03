package com.nik.hpm.code;

import lombok.Getter;

@Getter
public enum ReportLanguage {
	KO("국문"),
	EN("영문");
	
	private String name;
	
	ReportLanguage(String name){
		this.name = name;
	}
}
