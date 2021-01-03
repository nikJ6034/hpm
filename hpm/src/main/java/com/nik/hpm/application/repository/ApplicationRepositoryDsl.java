package com.nik.hpm.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.hpm.application.entity.Application;

public interface ApplicationRepositoryDsl {

	public Page<Application> applicationList(Application application, Pageable pageable);
}
