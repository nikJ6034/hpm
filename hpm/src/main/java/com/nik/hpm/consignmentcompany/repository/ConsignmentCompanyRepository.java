package com.nik.hpm.consignmentcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;

public interface ConsignmentCompanyRepository extends JpaRepository<ConsignmentCompany, Long>, ConsignmentCompanyRepositoryDsl{

}
