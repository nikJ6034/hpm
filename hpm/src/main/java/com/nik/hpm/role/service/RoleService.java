package com.nik.hpm.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.hpm.role.entity.Role;
import com.nik.hpm.role.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> roleList(){
		return roleRepository.findAll();
	}
}
