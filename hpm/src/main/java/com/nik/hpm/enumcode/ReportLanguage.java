package com.nik.hpm.enumcode;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReportLanguage implements Serializable{
	KO("국문"),
	EN("영문");
	
	private String name;
	private String value;
	
	ReportLanguage(String name){
		this.name = name;
		this.value= this.toString();
	}
}
