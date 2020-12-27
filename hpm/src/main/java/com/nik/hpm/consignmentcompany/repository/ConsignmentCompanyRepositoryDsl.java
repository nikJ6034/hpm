package com.nik.hpm.consignmentcompany.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.consignmentcompany.vo.ConsignmentCompanySearchVO;

public interface ConsignmentCompanyRepositoryDsl {

	public Page<ConsignmentCompany> companyList(ConsignmentCompanySearchVO consignmentCompanySearchVO, Pageable pageable);
}
