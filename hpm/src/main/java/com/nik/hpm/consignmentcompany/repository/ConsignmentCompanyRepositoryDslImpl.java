package com.nik.hpm.consignmentcompany.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.consignmentcompany.entity.QConsignmentCompany;
import com.nik.hpm.consignmentcompany.vo.ConsignmentCompanySearchVO;
import com.nik.hpm.enumcode.DelYn;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;

public class ConsignmentCompanyRepositoryDslImpl extends QuerydslRepositorySupport implements ConsignmentCompanyRepositoryDsl {

	public ConsignmentCompanyRepositoryDslImpl() {
		super(ConsignmentCompany.class);
	}

	@Override
	public Page<ConsignmentCompany> companyList(ConsignmentCompanySearchVO consignmentCompanySearchVO, Pageable pageable) {
		QConsignmentCompany consignmentcompany = QConsignmentCompany.consignmentCompany;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(StringUtils.isNotBlank(consignmentCompanySearchVO.getCondition())) {
			
			if(consignmentCompanySearchVO.getCondition().equals("name") && StringUtils.isNotBlank(consignmentCompanySearchVO.getKeyword())) {
				builder.and(consignmentcompany.name.contains(consignmentCompanySearchVO.getKeyword()));
			}
			
		}
		
		QueryResults<ConsignmentCompany> fetchResults = from(consignmentcompany)
		.where(consignmentcompany.delYn.eq(DelYn.N),builder)
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.fetchResults();
		
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}

}
