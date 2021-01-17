package com.nik.hpm.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.entity.QApplication;
import com.nik.hpm.application.vo.ApplicationSearchVO;
import com.nik.hpm.enumcode.Yn;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;

public class ApplicationRepositoryDslImpl extends QuerydslRepositorySupport implements ApplicationRepositoryDsl {

	public ApplicationRepositoryDslImpl() {
		super(Application.class);
	}

	@Override
	public Page<Application> applicationList(ApplicationSearchVO applicationSearchVO, Pageable pageable) {
		QApplication application = QApplication.application;
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(application.delYn.eq(Yn.N));
		if(applicationSearchVO.getCustomer() > 0) {
			booleanBuilder.and(application.customer.id.eq(applicationSearchVO.getCustomer()));
		}
		
		QueryResults<Application> fetchResults = from(application)
				.where(booleanBuilder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
				
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}

	@Override
	public Application application(Application app) {
		
		QApplication application = QApplication.application;
		
		JPQLQuery<Application> where = from(application)
		.where(application.id.eq(app.getId()));
		
		return where.fetchOne();
	}


}
