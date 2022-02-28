package com.nik.hpm.application.contorller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.service.ApplicationLogService;
import com.nik.hpm.application.service.ApplicationService;
import com.nik.hpm.application.vo.AppLogAllListVO;
import com.nik.hpm.application.vo.ApplicationSearchVO;
import com.nik.hpm.application.vo.ApplicationVO;
import com.nik.hpm.application.vo.LogAllVO;
import com.nik.hpm.code.service.CommonCodeService;
import com.nik.hpm.member.entity.Member;
import com.nik.hpm.util.file.excel.hpm.HpmApplicationExcel;

@RestController
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	ApplicationLogService applicationLogService;
	
	@Autowired
	CommonCodeService commonCodeService;
	
	@Autowired
	HpmApplicationExcel hpmApplicationExcel;
	
	@GetMapping(value= "/api/application/{id}")
	public ApplicationVO application(Application application) {
		
		
		ApplicationVO selectApplication = applicationService.application(application);
		
//		CommonCodeSearchVO commonCode = new CommonCodeSearchVO();
//		commonCode.setPCode("bizCondition");
//		commonCode.setCCode(selectApplication.getCustomer().getBizCondition());
//		CommonCode1depthVO cCode = commonCodeService.cCode(commonCode);
//		if(cCode != null) {
//			selectApplication.getCustomer().setBizCondition(cCode.getCodeDesc());
//		}
//		for(CommonCode cod : code.getCodeList()) {
//			if(selectApplication.getCustomer().getBizCondition().equals(cod.getCode())) {
//				selectApplication.getCustomer().setBizCondition(cod.getCodeDesc());
//			}
//		}
		
		return selectApplication;
	}
	
	@GetMapping(value= "/api/application")
	public 	Page<Application> applicationList(ApplicationSearchVO applicationSearchVO, @PageableDefault(size = 10)Pageable pageable){
		
		Page<Application> applicationList = applicationService.applicationList(applicationSearchVO, pageable);
		return applicationList;
	}
	
	@GetMapping(value= "/api/applicationAll")
    public  List<AppLogAllListVO> applicationAll(ApplicationSearchVO applicationSearchVO){
        
        return applicationService.applicationAll(applicationSearchVO);
    }
	
	@PostMapping(value= "/api/application")
	public Map<String, Object> applicationCreate(MultipartFile customerSignImgFile, @RequestPart("applicationVO")ApplicationVO applicationVO, Authentication authentication) throws IOException, Exception{
		Map<String, Object> map = new HashMap<>();
		OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        @SuppressWarnings("unchecked")
        Map<String, Object> decodedDetails = (LinkedHashMap<String, Object>)oAuth2AuthenticationDetails.getDecodedDetails();
        Member member = new Member();
        member.setId(Long.parseLong(decodedDetails.get("id").toString()));
        applicationVO.setRegMember(member);
		Application applicationCreate = applicationService.applicationCreate(applicationVO, customerSignImgFile);
		map.put("id", applicationCreate.getId());
		map.put("result", "success");
		map.put("msg", "등록 성공");
		
		return map;
	}
	
//	@PutMapping(value= "/api/application")
//	public Map<String, Object> applicationModify(@RequestPart("customerSignImgFile")MultipartFile customerSignImgFile, @RequestPart("applicationVO")ApplicationVO applicationVO) throws IOException, Exception{
//		Map<String, Object> map = new HashMap<>();
//		applicationService.applicationModify(applicationVO, customerSignImgFile);
//		map.put("result", "success");
//		map.put("msg", "등록 성공");
//		
//		return map;
//	}
	
	@DeleteMapping(value= "/api/application")
	public Map<String, Object> applicationDelete(@RequestBody Application application){
		Map<String, Object> map = new HashMap<>();
		applicationService.applicationDelete(application);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@GetMapping(value= "/api/excel/application/{id}")
	public void applicationExcel(Application application, HttpServletResponse response) throws IOException {
		
//		CommonCode commonCode = new CommonCode();
//		commonCode.setCode("bizCondition");
//		CommonCode code = commonCodeService.codeList(commonCode);
		ApplicationVO selectApplication = applicationService.application(application);
//		for(CommonCode cod : code.getCodeList()) {
//			if(selectApplication.getCustomer().getBizCondition().equals(cod.getCode())) {
//				selectApplication.getCustomer().setBizCondition(cod.getCodeDesc());
//			}
//		}
		File writeExcel = hpmApplicationExcel.writeExcel(selectApplication);
		
		ServletOutputStream outputStream = response.getOutputStream();
		FileUtils.copyFile(writeExcel, outputStream);
		
		outputStream.close();
	}
	
	@GetMapping(value= "/api/logAll")
    public  List<LogAllVO> logAll(ApplicationSearchVO applicationSearchVO){
        
        return applicationService.logAll(applicationSearchVO);
    }
	
}
