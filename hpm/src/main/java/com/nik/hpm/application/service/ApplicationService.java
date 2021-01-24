package com.nik.hpm.application.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.application.repository.ApplicationLogRepository;
import com.nik.hpm.application.repository.ApplicationRepository;
import com.nik.hpm.application.vo.ApplicationSearchVO;
import com.nik.hpm.application.vo.ApplicationVO;
import com.nik.hpm.attachfile.entity.AttachFile;
import com.nik.hpm.attachfile.repository.AttachFileRepository;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.util.file.image.UploadResourceImageFileUtil;

@Service
@Transactional
public class ApplicationService {
	
	@Autowired
	ApplicationRepository applicationRepository;
	@Autowired
	ApplicationLogRepository applicationLogRepository;
	@Autowired
	AttachFileRepository attachFileRepository;
	@Autowired
	UploadResourceImageFileUtil uploadResourceImageFileUtil;
	
	public Application application(Application application) {
//		Optional<Application> findById = applicationRepository.findById(application.getId());
//		return findById.get();
		
		Application selectApplication = applicationRepository.application(application);
		
		return selectApplication;
	}
	
	
	public Page<Application> applicationList(ApplicationSearchVO applicationSearchVO, Pageable pageable){
		return applicationRepository.applicationList(applicationSearchVO, pageable);
	}
	
	public void applicationModify(ApplicationVO applicationVO, MultipartFile customerSignImgFile) throws IOException, Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		Application application = modelMapper.map(applicationVO, Application.class);
		
		if(customerSignImgFile != null && customerSignImgFile.getSize() > 0) {
			AttachFile imageUpload = uploadResourceImageFileUtil.imageUpload(customerSignImgFile.getOriginalFilename(), customerSignImgFile.getBytes());
			attachFileRepository.save(imageUpload);
			application.setCustomerSignImg(imageUpload);
		}
		
		application.setDelYn(Yn.N);
		applicationRepository.save(application);
		
		if(applicationVO.getApplicationLogList() != null) {
			applicationVO.getApplicationLogList().forEach(log->{
				ConsignmentCompany consignmentCompany = new ConsignmentCompany();
				consignmentCompany.setId(log.getConsignmentCompany());
				ApplicationLog applicationLog = modelMapper.map(log, ApplicationLog.class);
				
				if(log.isDel()) {
					applicationLog.setConsignmentCompany(consignmentCompany);
					applicationLog.setApplication(application);
					applicationLogRepository.delete(applicationLog);
				}
				
				if(log.isEdite()) {
					applicationLog.setConsignmentCompany(consignmentCompany);
					applicationLog.setApplication(application);
					applicationLogRepository.save(applicationLog);
				}
				
			});
		}
		
	}
	
	public void applicationCreate(ApplicationVO applicationVO, MultipartFile customerSignImgFile) throws IOException, Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		Application application = modelMapper.map(applicationVO, Application.class);
		
		application.setRegDate(LocalDate.now());
		
		application.setDelYn(Yn.N);
		if(customerSignImgFile != null && customerSignImgFile.getSize() > 0) {
			AttachFile imageUpload = uploadResourceImageFileUtil.imageUpload(customerSignImgFile.getOriginalFilename(), customerSignImgFile.getBytes());
			attachFileRepository.save(imageUpload);
			application.setCustomerSignImg(imageUpload);
		}
		
		Application app = applicationRepository.save(application);
		
		if(applicationVO.getApplicationLogList() != null) {
			applicationVO.getApplicationLogList().forEach(log -> {
				ConsignmentCompany consignmentCompany = new ConsignmentCompany();
				consignmentCompany.setId(log.getConsignmentCompany());
				ApplicationLog applicationLog = modelMapper.map(log, ApplicationLog.class);
				if(log.isDel()) {
					applicationLog.setConsignmentCompany(consignmentCompany);
					applicationLog.setApplication(application);
					applicationLogRepository.delete(applicationLog);
				}
				
				if(log.isEdite()) {
					applicationLog.setConsignmentCompany(consignmentCompany);
					applicationLog.setApplication(app);
					applicationLogRepository.save(applicationLog);
				}
			});
		}
		
		
	}
	
	public void applicationDelete(Application application) {
		Optional<Application> findById = applicationRepository.findById(application.getId());
		findById.ifPresent(com->{
			com.setDelYn(Yn.Y);
		});
		
	}

}
