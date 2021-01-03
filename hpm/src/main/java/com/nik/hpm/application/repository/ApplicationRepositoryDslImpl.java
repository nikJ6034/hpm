package com.nik.hpm.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.entity.QApplication;
import com.querydsl.core.QueryResults;

public class ApplicationRepositoryDslImpl extends QuerydslRepositorySupport implements ApplicationRepositoryDsl {

	public ApplicationRepositoryDslImpl() {
		super(Application.class);
	}

	@Override
	public Page<Application> applicationList(Application app, Pageable pageable) {
		QApplication application = QApplication.application;
		
		QueryResults<Application> fetchResults = from(application)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
				
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}


}
