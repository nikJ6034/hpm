package com.nik.hpm.member.entity;

import java.io.Serializable;

import com.nik.hpm.role.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberRolePK implements Serializable{

	private static final long serialVersionUID = 1L;

	private Member member;
	
	private Role role;
}
