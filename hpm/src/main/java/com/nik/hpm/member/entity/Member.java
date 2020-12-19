package com.nik.hpm.member.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String memberId;
	@Column
	private String name;	
	
	@Column
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String memberPassword;

	@Column
	private String email;

	@Column String mobile;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="member_id", referencedColumnName = "id")
	List<MemberRolegroup> memberRolegroupList;
	
}
