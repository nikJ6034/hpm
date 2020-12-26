package com.nik.hpm.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.member.entity.MemberRole;
import com.nik.hpm.member.entity.MemberRolePK;

public interface MemberRoleRepository extends JpaRepository<MemberRole, MemberRolePK>{
	
}
