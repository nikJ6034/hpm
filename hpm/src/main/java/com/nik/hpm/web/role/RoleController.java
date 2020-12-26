package com.nik.hpm.web.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.role.entity.Role;
import com.nik.hpm.role.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;
	
	
	@GetMapping(value="/api/role")
	public List<Role> roleList(){
		return roleService.roleList();
	}
	
}
