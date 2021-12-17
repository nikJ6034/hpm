package com.nik.hpm.application.contorller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.application.service.ApplicationLogService;
import com.nik.hpm.application.vo.ApplicationLogVO;
import com.nik.hpm.application.vo.LogAllExcelVO;
import com.nik.hpm.util.file.excel.data.DataAppliLogExcel;

@RestController
public class ApplicationLogController {

	@Autowired
	ApplicationLogService applicationLogService;
	@Autowired
	DataAppliLogExcel dataAppliLogExcel;
	
	
	@PutMapping(value= "/api/applicationlog")
	public Map<String, Object> companyModify(@RequestBody List<ApplicationLogVO> applicationLogList){
		Map<String, Object> map = new HashMap<>();
		applicationLogService.applicationLogCreate(applicationLogList);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@PostMapping(value= "/api/applicationlog/excel")
	public void appliLogExcel(@RequestBody List<LogAllExcelVO> applicationLogList, HttpServletResponse response) throws IOException{
		
		Map<String, Map<Long, List<LogAllExcelVO>>> collect = applicationLogList.stream()
		.collect(groupingBy(LogAllExcelVO::getDeviceName, groupingBy(LogAllExcelVO::getCorrectionFee)));
		
		List<LogAllExcelVO> list = new ArrayList<>();
		
		Iterator<String> iterator = collect.keySet().iterator();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			Map<Long, List<LogAllExcelVO>> value = collect.get(key);
			Iterator<Long> iterator2 = value.keySet().iterator();
			
			while(iterator2.hasNext()) {
				LogAllExcelVO applicationLog = new LogAllExcelVO();
				Long key2 = iterator2.next();
				
				List<LogAllExcelVO> value2 = value.get(key2);
				
				int size = 0;
				
				if(value2 != null && !value2.isEmpty()) {
					size = value2.size();
					applicationLog.setConsignmentCompanyNm(value2.get(0).getConsignmentCompanyNm());
					applicationLog.setRequestCustomerName(value2.get(0).getRequestCustomerName());
					applicationLog.setCustomerName(value2.get(0).getCustomerName());
				}
				applicationLog.setQuantity(size);
				applicationLog.setDeviceName(key);
				applicationLog.setCorrectionFee(key2);
				list.add(applicationLog);
			}
			
		}
		LogAllExcelVO logAllExcelVO = applicationLogList.get(0);
		
		File writeExcel = dataAppliLogExcel.writeExcelEstimate(applicationLogList,logAllExcelVO, list);
		
		ServletOutputStream outputStream = response.getOutputStream();
		FileUtils.copyFile(writeExcel, outputStream);
		
		outputStream.close();
	}
	
}
