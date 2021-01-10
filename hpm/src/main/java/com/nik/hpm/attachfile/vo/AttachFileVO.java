package com.nik.hpm.attachfile.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttachFileVO {
	@Id @GeneratedValue
    private Long id;
    private String realName;
    private String virtualName;
    private String extension;
    private String thumbnailYN = "N";
    private Long regId;
    private String resourcePath;
}
