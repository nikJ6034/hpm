package com.nik.hpm.menu.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.nik.hpm.role.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MenuRole {
	
	@EmbeddedId
	private MenuRoleKey roleMenu;
	
}

@Embeddable
@Getter @Setter
class MenuRoleKey implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="menu_id", referencedColumnName="id")
	private Menu menu;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="role_id", referencedColumnName="id")
	private Role role;

}
