package com.nik.hpm.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.application.entity.ApplicationLog;

public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Long>, ApplicationLogRepositoryDsl{

}
