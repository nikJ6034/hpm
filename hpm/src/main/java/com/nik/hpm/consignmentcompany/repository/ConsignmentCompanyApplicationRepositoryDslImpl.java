package com.nik.hpm.consignmentcompany.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompanyApplication;
import com.nik.hpm.consignmentcompany.entity.QConsignmentCompanyApplication;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;

public class ConsignmentCompanyApplicationRepositoryDslImpl extends QuerydslRepositorySupport implements ConsignmentCompanyApplicationRepositoryDsl {

	public ConsignmentCompanyApplicationRepositoryDslImpl() {
		super(ConsignmentCompanyApplication.class);
	}

	@Override
	public Page<ConsignmentCompanyApplication> companyAppList(ConsignmentCompanyApplication consignmentCompanyApplication, Pageable pageable) {
		QConsignmentCompanyApplication qConsignmentCompanyApplication = QConsignmentCompanyApplication.consignmentCompanyApplication;
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		if(consignmentCompanyApplication.getConsignmentCompany() != null && consignmentCompanyApplication.getConsignmentCompany().getId() > 0 ) {
			booleanBuilder.and(qConsignmentCompanyApplication.consignmentCompany.eq(consignmentCompanyApplication.getConsignmentCompany()));
		}
		
		QueryResults<ConsignmentCompanyApplication> fetchResults = from(qConsignmentCompanyApplication)
		.where(booleanBuilder)
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.orderBy(qConsignmentCompanyApplication.startDt.desc())
		.fetchResults();
		
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}

}
