package com.nik.hpm.consignmentcompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.enumcode.Yn;

public interface ConsignmentCompanyRepository extends JpaRepository<ConsignmentCompany, Long>, ConsignmentCompanyRepositoryDsl{

	List<ConsignmentCompany> findByDelYn(Yn delYn);
}
