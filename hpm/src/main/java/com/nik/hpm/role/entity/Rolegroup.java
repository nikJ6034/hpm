package com.nik.hpm.role.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Rolegroup {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String rolegroupName;
	
	@Column
	private String rolegroupDescription;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "rolegroup_id", referencedColumnName = "id")
	List<RolegroupRole> roleGroupRoleList;
	
	
}

