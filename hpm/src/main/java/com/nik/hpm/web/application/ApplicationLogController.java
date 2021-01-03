package com.nik.hpm.web.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.application.service.ApplicationLogService;

@RestController
public class ApplicationLogController {

	@Autowired
	ApplicationLogService applicationLogService;
	
	
	@PutMapping(value= "/api/applicationlog")
	public Map<String, Object> companyModify(@RequestBody List<ApplicationLog> applicationLogList){
		Map<String, Object> map = new HashMap<>();
		applicationLogService.applicationLogCreate(applicationLogList);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
}
