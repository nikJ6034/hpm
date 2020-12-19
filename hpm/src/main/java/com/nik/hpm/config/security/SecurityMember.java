package com.nik.hpm.config.security;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.nik.hpm.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SecurityMember extends User{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String memberId;
	private String name;
	
	public SecurityMember(Member member, Set<GrantedAuthority> authorities) {
		super(member.getMemberId(), member.getMemberPassword(), authorities);
		this.id = member.getId();
		this.memberId = member.getMemberId();
		this.name = member.getName();
	}

}
