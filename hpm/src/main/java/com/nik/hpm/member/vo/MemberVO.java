package com.nik.hpm.member.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVO {

	private long id;
	private String memberId;
	private String name;	
	private String memberPassword;
	private String passwordConfirm;
	private String email;
	private String mobile;
	private long roleId;
}
