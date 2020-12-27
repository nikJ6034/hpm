package com.nik.hpm.web.consignmentCompany;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.consignmentcompany.service.ConsignmentCompanyService;
import com.nik.hpm.consignmentcompany.vo.ConsignmentCompanySearchVO;

@RestController
public class ConsignmentCompanyController {

	@Autowired
	ConsignmentCompanyService consignmentCompanyService;
	
	@GetMapping(value= "/api/company/{id}")
	public ConsignmentCompany company(ConsignmentCompany consignmentCompany) {
		return consignmentCompanyService.company(consignmentCompany);
	}

	@GetMapping(value= "/api/company")
	public 	Page<ConsignmentCompany> companyList(ConsignmentCompanySearchVO consignmentCompanySearchVO, @PageableDefault(size = 10)Pageable pageable){
		Page<ConsignmentCompany> companyList = consignmentCompanyService.companyList(consignmentCompanySearchVO, pageable);
		return companyList;
	}
	
	@PostMapping(value= "/api/company")
	public Map<String, Object> companyCreate(@RequestBody ConsignmentCompany consignmentCompany){
		Map<String, Object> map = new HashMap<>();
		consignmentCompanyService.companyCreate(consignmentCompany);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@DeleteMapping(value= "/api/company")
	public Map<String, Object> companyDelete(@RequestBody ConsignmentCompany consignmentCompany){
		Map<String, Object> map = new HashMap<>();
		consignmentCompanyService.companyDelete(consignmentCompany);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@PutMapping(value= "/api/company")
	public Map<String, Object> companyModify(@RequestBody ConsignmentCompany consignmentCompany){
		Map<String, Object> map = new HashMap<>();
		consignmentCompanyService.companyModify(consignmentCompany);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
}
