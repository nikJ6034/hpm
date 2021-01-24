package com.nik.hpm.consignmentcompany.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.application.repository.ApplicationLogRepository;
import com.nik.hpm.application.vo.ApplicationLogSearchVO;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompanyApplication;
import com.nik.hpm.consignmentcompany.repository.ConsignmentCompanyApplicationRepository;

@Service
@Transactional
public class ConsignmentCompanyApplicationService {

	@Autowired
	ConsignmentCompanyApplicationRepository consignmentCompanyApplicationRepository;
	@Autowired
	ApplicationLogRepository applicationLogRepository; 
	
	public ConsignmentCompanyApplication companyApp(ConsignmentCompanyApplication consignmentCompanyApplication) {
		Optional<ConsignmentCompanyApplication> findById = consignmentCompanyApplicationRepository.findById(consignmentCompanyApplication.getId());
		List<ApplicationLog> applicationLogList = null;
		ConsignmentCompanyApplication selectConsignmentCompanyApplication = null;
		ApplicationLogSearchVO applicationLogSearchVO = new ApplicationLogSearchVO();
		
		if(findById.isPresent()) {
			selectConsignmentCompanyApplication = findById.get();
			applicationLogSearchVO.setStartDt(selectConsignmentCompanyApplication.getStartDt());
			applicationLogSearchVO.setEndDt(selectConsignmentCompanyApplication.getEndDt());
			applicationLogSearchVO.setConsignmentCompany(selectConsignmentCompanyApplication.getConsignmentCompany());
			applicationLogList = applicationLogRepository.applicationLogList(applicationLogSearchVO);
			
		}else {
			
			selectConsignmentCompanyApplication = new ConsignmentCompanyApplication();
			
			selectConsignmentCompanyApplication.setStartDt(consignmentCompanyApplication.getStartDt());
			selectConsignmentCompanyApplication.setEndDt(consignmentCompanyApplication.getEndDt());
			selectConsignmentCompanyApplication.setConsignmentCompany(consignmentCompanyApplication.getConsignmentCompany());
			
			applicationLogSearchVO.setStartDt(consignmentCompanyApplication.getStartDt());
			applicationLogSearchVO.setEndDt(consignmentCompanyApplication.getEndDt());
			applicationLogSearchVO.setConsignmentCompany(consignmentCompanyApplication.getConsignmentCompany());
			
			applicationLogList = applicationLogRepository.applicationLogList(applicationLogSearchVO);
			
		}
		
		selectConsignmentCompanyApplication.setApplicationLogList(applicationLogList);
		
		return selectConsignmentCompanyApplication;
	}
	
	
	public Page<ConsignmentCompanyApplication> companyAppList(ConsignmentCompanyApplication consignmentCompanyApplication, Pageable pageable){
		return consignmentCompanyApplicationRepository.companyAppList(consignmentCompanyApplication, pageable);
	}
	
	
	public void companyAppCreate(ConsignmentCompanyApplication consignmentCompanyApplication) {
		consignmentCompanyApplicationRepository.save(consignmentCompanyApplication);
	}
	
	public void companyAppDelete(ConsignmentCompanyApplication consignmentCompanyApplication) {
		
		consignmentCompanyApplicationRepository.delete(consignmentCompanyApplication);
	}
}
