package com.nik.hpm.role.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class RolegroupRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonBackReference
	private Rolegroup rolegroup;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonIgnore
	private Role role;
	
}

//@Embeddable
//@Getter @Setter
//class RoleGroupRoleKey implements Serializable{
//	
//	private static final long serialVersionUID = 1L;
//	
//	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	private Rolegroup rolegroup;
//	
//	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	private Role role;
//	
//
//}