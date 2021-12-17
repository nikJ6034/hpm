package com.nik.hpm.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.vo.AppLogAllListVO;
import com.nik.hpm.application.vo.ApplicationSearchVO;
import com.nik.hpm.application.vo.LogAllVO;

public interface ApplicationRepositoryDsl {

	public Page<Application> applicationList(ApplicationSearchVO applicationSearchVO, Pageable pageable);
	
	public List<AppLogAllListVO> applicationAll(ApplicationSearchVO applicationSearchVO);
	
	public Application application(Application application);
	
	public List<LogAllVO> logAll(ApplicationSearchVO applicationSearchVO);
}
