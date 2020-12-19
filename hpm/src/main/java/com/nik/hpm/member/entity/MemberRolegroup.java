package com.nik.hpm.member.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nik.hpm.role.entity.Rolegroup;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MemberRolegroup implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonBackReference
	private Member member;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonIgnore
	private Rolegroup rolegroup;

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