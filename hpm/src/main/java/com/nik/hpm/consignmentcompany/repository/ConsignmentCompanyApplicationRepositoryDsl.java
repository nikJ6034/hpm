package com.nik.hpm.consignmentcompany.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompanyApplication;

public interface ConsignmentCompanyApplicationRepositoryDsl {

	public Page<ConsignmentCompanyApplication> companyAppList(ConsignmentCompanyApplication consignmentCompanyApplication, Pageable pageable);
}
