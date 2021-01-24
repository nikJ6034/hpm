package com.nik.hpm.application.repository;

import java.util.List;

import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.application.vo.ApplicationLogSearchVO;

public interface ApplicationLogRepositoryDsl {

	public List<ApplicationLog> applicationLogList(ApplicationLogSearchVO applicationLogSearchVO);
	
}
