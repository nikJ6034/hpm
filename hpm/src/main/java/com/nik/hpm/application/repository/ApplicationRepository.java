package com.nik.hpm.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.application.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>, ApplicationRepositoryDsl{

}
