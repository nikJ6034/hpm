package com.nik.hpm.web.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.application.service.ApplicationLogService;
import com.nik.hpm.application.service.ApplicationService;

@RestController
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	ApplicationLogService applicationLogService;
	
	@GetMapping(value= "/api/application/{id}")
	public Application company(Application application) {
		return applicationService.application(application);
	}
	
	@GetMapping(value= "/api/application")
	public 	Page<Application> companyList(Application application, @PageableDefault(size = 10)Pageable pageable){
		Page<Application> applicationList = applicationService.applicationList(application, pageable);
		return applicationList;
	}
	
	@PostMapping(value= "/api/application")
	public Map<String, Object> companyCreate(@RequestBody Application application, @RequestBody List<ApplicationLog> applicationLogList){
		Map<String, Object> map = new HashMap<>();
		applicationService.applicationCreate(application);
		
		map.put("result", "success");
		map.put("msg", "등록 성공");
		
		return map;
	}
	
	@DeleteMapping(value= "/api/application")
	public Map<String, Object> companyDelete(@RequestBody Application application){
		Map<String, Object> map = new HashMap<>();
		applicationService.applicationDelete(application);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	
}
