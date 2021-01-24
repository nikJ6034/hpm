package com.nik.hpm.consignmentcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompanyApplication;

public interface ConsignmentCompanyApplicationRepository extends JpaRepository<ConsignmentCompanyApplication, Long>, ConsignmentCompanyApplicationRepositoryDsl{

}
