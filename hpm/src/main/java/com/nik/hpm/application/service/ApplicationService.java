package com.nik.hpm.application.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.repository.ApplicationRepository;
import com.nik.hpm.enumcode.DelYn;

@Service
@Transactional
public class ApplicationService {
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	public Application application(Application application) {
		Optional<Application> findById = applicationRepository.findById(application.getId());
		
		return findById.get();
	}
	
	
	public Page<Application> applicationList(Application application, Pageable pageable){
		return applicationRepository.applicationList(application, pageable);
	}
	
//	public void companyModify(Application application) {
//		Optional<Application> findById = applicationRepository.findById(application.getId());
//		
//	}
	
	public void applicationCreate(Application application) {
		application.setDelYn(DelYn.N);
		applicationRepository.save(application);
	}
	
	public void applicationDelete(Application application) {
		Optional<Application> findById = applicationRepository.findById(application.getId());
		findById.ifPresent(com->{
			com.setDelYn(DelYn.Y);
		});
		
	}

}
