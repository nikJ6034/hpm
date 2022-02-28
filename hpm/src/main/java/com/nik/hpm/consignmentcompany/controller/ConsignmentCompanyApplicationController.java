package com.nik.hpm.consignmentcompany.controller;

import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.application.vo.LogAllExcelVO;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompanyApplication;
import com.nik.hpm.consignmentcompany.service.ConsignmentCompanyApplicationService;
import com.nik.hpm.util.file.excel.consign.ConsignApplicationExcel;
import com.nik.hpm.util.file.excel.data.DataAppliLogExcel;

@RestController
public class ConsignmentCompanyApplicationController {

	@Autowired
	ConsignmentCompanyApplicationService consignmentCompanyApplicationService;
	@Autowired
	ConsignApplicationExcel consignApplicationExcel;
	@Autowired
	DataAppliLogExcel dataAppliLogExcel;
	
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
		ConsignmentCompanyApplication companyAppCreate = consignmentCompanyApplicationService.companyAppCreate(consignmentCompanyApplication);
		map.put("id", companyAppCreate.getId());
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
	
	@GetMapping(value= "/api/excel/companyApp/{id}")
	public void excelCompanyApp(ConsignmentCompanyApplication consignmentCompanyApplication, HttpServletResponse response) throws IOException {
		ConsignmentCompanyApplication companyApp = consignmentCompanyApplicationService.companyApp(consignmentCompanyApplication);
		
		File writeExcel = consignApplicationExcel.writeExcel(companyApp);
		
		ServletOutputStream outputStream = response.getOutputStream();
		FileUtils.copyFile(writeExcel, outputStream);
		
		outputStream.close();
	}
	
//	@PostMapping(value= "/api/companyApp/estimate/excel")
//	public void appliLogExcel(@RequestBody List<LogAllExcelVO> applicationLogList, HttpServletResponse response) throws IOException{
//		
//		Map<String, Map<Long, List<LogAllExcelVO>>> collect = applicationLogList.stream()
//		.collect(groupingBy(LogAllExcelVO::getDeviceName, groupingBy(LogAllExcelVO::getCorrectionFee)));
//		
//		List<LogAllExcelVO> list = new ArrayList<>();
//		
//		Iterator<String> iterator = collect.keySet().iterator();
//		
//		while(iterator.hasNext()) {
//			String key = iterator.next();
//			Map<Long, List<LogAllExcelVO>> value = collect.get(key);
//			Iterator<Long> iterator2 = value.keySet().iterator();
//			
//			while(iterator2.hasNext()) {
//				LogAllExcelVO applicationLog = new LogAllExcelVO();
//				Long key2 = iterator2.next();
//				
//				List<LogAllExcelVO> value2 = value.get(key2);
//				
//				int size = 0;
//				
//				if(value2 != null && !value2.isEmpty()) {
//					size = value2.size();
//					applicationLog.setConsignmentCompanyNm(value2.get(0).getConsignmentCompanyNm());
//					applicationLog.setRequestCustomerName(value2.get(0).getRequestCustomerName());
//					applicationLog.setCustomerName(value2.get(0).getCustomerName());
//				}
//				applicationLog.setQuantity(size);
//				applicationLog.setDeviceName(key);
//				applicationLog.setCorrectionFee(key2);
//				list.add(applicationLog);
//			}
//			
//		}
//		LogAllExcelVO logAllExcelVO = applicationLogList.get(0);
//		
//		File writeExcel = dataAppliLogExcel.writeExcelConsignEstimate(applicationLogList,logAllExcelVO, list);
//		
//		ServletOutputStream outputStream = response.getOutputStream();
//		FileUtils.copyFile(writeExcel, outputStream);
//		
//		outputStream.close();
//	}
	
}
