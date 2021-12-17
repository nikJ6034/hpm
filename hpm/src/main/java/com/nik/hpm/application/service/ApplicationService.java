package com.nik.hpm.application.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.application.repository.ApplicationLogRepository;
import com.nik.hpm.application.repository.ApplicationRepository;
import com.nik.hpm.application.vo.AppLogAllListVO;
import com.nik.hpm.application.vo.ApplicationSearchVO;
import com.nik.hpm.application.vo.ApplicationVO;
import com.nik.hpm.application.vo.LogAllVO;
import com.nik.hpm.attachfile.entity.AttachFile;
import com.nik.hpm.attachfile.repository.AttachFileRepository;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.util.file.image.UploadResourceImageFileUtil;
import com.nimbusds.oauth2.sdk.util.StringUtils;

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
	
	public ApplicationVO application(Application application) {
//		Optional<Application> findById = applicationRepository.findById(application.getId());
//		return findById.get();
		
		Application selectApplication = applicationRepository.application(application);
		
		return new ApplicationVO(selectApplication);
	}
	
	
	public Page<Application> applicationList(ApplicationSearchVO applicationSearchVO, Pageable pageable){
		return applicationRepository.applicationList(applicationSearchVO, pageable);
	}
	
	public List<AppLogAllListVO> applicationAll(ApplicationSearchVO applicationSearchVO){
        return applicationRepository.applicationAll(applicationSearchVO);
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
				consignmentCompany.setId(log.getConsignmentCompany().getId());
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
	
	public Application applicationCreate(ApplicationVO applicationVO, MultipartFile customerSignImgFile) throws IOException, Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
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
				
				if(StringUtils.isNotBlank(log.getConsignmentCompanyId())) {
					consignmentCompany.setId(Integer.parseInt(log.getConsignmentCompanyId()));
				}
				
				ApplicationLog applicationLog = modelMapper.map(log, ApplicationLog.class);
				if(log.isDel()) {
					applicationLog.setConsignmentCompany(consignmentCompany);
					applicationLog.setApplication(application);
					applicationLogRepository.delete(applicationLog);
				}
				
				if(log.isEdite()) {
					if(consignmentCompany.getId() == 0) {
						applicationLog.setConsignmentCompany(null);
					}else {
						applicationLog.setConsignmentCompany(consignmentCompany);
					}
					applicationLog.setApplication(app);
					applicationLogRepository.save(applicationLog);
				}
			});
		}
		
		return app;
		
	}
	
	public void applicationDelete(Application application) {
		Optional<Application> findById = applicationRepository.findById(application.getId());
		findById.ifPresent(com->{
			com.setDelYn(Yn.Y);
		});
		
	}
	
	public List<LogAllVO> logAll(ApplicationSearchVO applicationSearchVO){
        return applicationRepository.logAll(applicationSearchVO);
    }

}
