package com.nik.hpm.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nik.hpm.enumcode.DelYn;
import com.nik.hpm.role.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	@NotNull
	private String memberId;
	@Column
	@NotNull
	private String name;	
	
	@Column
	@NotNull
	@Size(min = 10)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String memberPassword;

	@Column
	private String email;

	@Column 
	private String mobile;
	
	@Enumerated(EnumType.STRING)
	@Column DelYn delYn;
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name="member_id", referencedColumnName = "id")
//	List<MemberRole> memberRoleList;
	
	@OneToOne
	@NotNull
	private Role role;
	
}
