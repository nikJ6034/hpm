package com.nik.hpm.member.vo;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberPasswordChangeVO {

	@NotNull
	private long id;
	@NotNull
	@Size(min = 10)
	private String memberPassword;
	@NotNull
	@Size(min = 10)
	private String passwordConfirm;
}
