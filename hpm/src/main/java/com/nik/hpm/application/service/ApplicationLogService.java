package com.nik.hpm.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.application.repository.ApplicationLogRepository;

@Service
@Transactional
public class ApplicationLogService {

	@Autowired
	ApplicationLogRepository applicationLogRepository;
	
	public List<ApplicationLog> findAll(){
		return applicationLogRepository.findAll();
	}
	
	public void applicationLogCreate(List<ApplicationLog> applicationLogList) {
		
		applicationLogList.forEach(item ->{
			applicationLogRepository.save(item);
		});
	}
}
