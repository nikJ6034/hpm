package com.nik.hpm.web.consignmentCompanyApplication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompanyApplication;
import com.nik.hpm.consignmentcompany.service.ConsignmentCompanyApplicationService;

@RestController
public class ConsignmentCompanyApplicationController {

	@Autowired
	ConsignmentCompanyApplicationService consignmentCompanyApplicationService;
	
	@GetMapping(value= "/api/companyApp/{id}")
	public ConsignmentCompanyApplication companyApp(ConsignmentCompanyApplication consignmentCompanyApplication) {
		return consignmentCompanyApplicationService.companyApp(consignmentCompanyApplication);
	}
	
	@GetMapping(value= "/api/companyApp")
	public 	Page<ConsignmentCompanyApplication> companyAppList(ConsignmentCompanyApplication consignmentCompanyApplication, @PageableDefault(size = 10)Pageable pageable){
		Page<ConsignmentCompanyApplication> companyList = consignmentCompanyApplicationService.companyAppList(consignmentCompanyApplication, pageable);
		return companyList;
	}
	
	@PostMapping(value= "/api/companyApp")
	public Map<String, Object> companyAppCreate(@RequestBody ConsignmentCompanyApplication consignmentCompanyApplication){
		Map<String, Object> map = new HashMap<>();
		consignmentCompanyApplicationService.companyAppCreate(consignmentCompanyApplication);
		
		map.put("result", "success");
		map.put("msg", "등록 성공");
		
		return map;
	}
	
	@DeleteMapping(value= "/api/companyApp")
	public Map<String, Object> companyAppDelete(@RequestBody ConsignmentCompanyApplication consignmentCompanyApplication){
		Map<String, Object> map = new HashMap<>();
		consignmentCompanyApplicationService.companyAppDelete(consignmentCompanyApplication);
		
		map.put("result", "success");
		map.put("msg", "삭제 성공");
		
		return map;
	}
	
}
