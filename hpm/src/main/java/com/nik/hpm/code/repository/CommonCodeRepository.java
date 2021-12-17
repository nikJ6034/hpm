package com.nik.hpm.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.code.entity.CommonCode;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long>, CommonCodeRepositoryDsl{
	
	CommonCode findByCode(String code);
	
	long countByCodeAndPid(String code, CommonCode commonCode);

}
