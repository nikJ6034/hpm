package com.nik.hpm.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.vo.ApplicationSearchVO;

public interface ApplicationRepositoryDsl {

	public Page<Application> applicationList(ApplicationSearchVO applicationSearchVO, Pageable pageable);
	
	public Application application(Application application);
}
