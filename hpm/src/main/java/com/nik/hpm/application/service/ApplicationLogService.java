package com.nik.hpm.application.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.application.repository.ApplicationLogRepository;
import com.nik.hpm.application.vo.ApplicationLogVO;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;

@Service
@Transactional
public class ApplicationLogService {

	@Autowired
	ApplicationLogRepository applicationLogRepository;
	
	public List<ApplicationLog> findAll(){
		return applicationLogRepository.findAll();
	}
	
	public void applicationLogCreate(List<ApplicationLogVO> applicationLogList) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		applicationLogList.forEach(item ->{
			
			ApplicationLog map = modelMapper.map(item, ApplicationLog.class);
			Optional<ApplicationLog> findById = applicationLogRepository.findById(map.getId());
			ApplicationLog applicationLog = findById.get();
			if(item.getConsignmentCompany() != null && item.getConsignmentCompany().getId() != 0) {
				ConsignmentCompany consignmentCompany = new ConsignmentCompany();
				consignmentCompany.setId(item.getConsignmentCompany().getId());
				applicationLog.setConsignmentCompany(consignmentCompany);
			}else {
				applicationLog.setConsignmentCompany(null);
			}
			applicationLog.setInspectionType(map.getInspectionType());
			applicationLog.setRegNumber(map.getRegNumber());
			applicationLog.setDeviceName(map.getDeviceName());
			applicationLog.setDeviceNumber(map.getDeviceNumber());
			applicationLog.setProductionCompany(map.getProductionCompany());
			applicationLog.setModel(map.getModel());
			applicationLog.setStandard(map.getStandard());
			applicationLog.setResolution(map.getResolution());
			applicationLog.setQuantity(map.getQuantity());
			applicationLog.setReportNumber(map.getReportNumber());
			applicationLog.setUnit(map.getUnit());
			applicationLog.setEtc(map.getEtc());
			applicationLog.setCarryType(map.getCarryType());
			applicationLog.setCorrectionFee(map.getCorrectionFee());
			applicationLog.setCorrectionDate(map.getCorrectionDate());
			applicationLog.setPlace(map.getPlace());
			applicationLog.setPractitioner(map.getPractitioner());
			applicationLog.setMiddleCategory(map.getMiddleCategory());
			applicationLog.setSmallCategory(map.getSmallCategory());
			applicationLog.setPublishedDate(map.getPublishedDate());
			applicationLog.setMarginRate(map.getMarginRate());
			applicationLog.setPaymentStateType(map.getPaymentStateType());
			
		});
	}
}
