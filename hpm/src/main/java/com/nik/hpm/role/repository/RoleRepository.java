package com.nik.hpm.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.role.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
