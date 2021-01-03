package com.nik.hpm.application.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.application.entity.ApplicationLog;

public class ApplicationLogRepositoryDslImpl extends QuerydslRepositorySupport implements ApplicationLogRepositoryDsl {

	public ApplicationLogRepositoryDslImpl() {
		super(ApplicationLog.class);
	}


}
