package com.nik.hpm.member.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nik.hpm.role.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Entity @IdClass(MemberRolePK.class)
@Getter @Setter
public class MemberRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JsonBackReference
	private Member member;
	
	@Id
	@ManyToOne
	//@JsonIgnore
	private Role role;

}


//@Embeddable
//@Getter @Setter
//class RoleMemberKey  implements Serializable{
//	private static final long serialVersionUID = 1L;
//	
//	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JsonBackReference
//	private Member member;
//	
//	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JsonIgnore
//	private Rolegroup rolegroup;
//	
//}