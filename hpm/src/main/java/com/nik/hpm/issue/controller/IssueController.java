package com.nik.hpm.issue.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nik.hpm.issue.entity.IssueLog;
import com.nik.hpm.issue.service.IssueLogService;
import com.nik.hpm.issue.vo.IssueLogSearchVO;
import com.nik.hpm.issue.vo.IssueLogVO;
import com.nik.hpm.util.file.excel.data.IssueLogExcel;

@RestController
public class IssueController {
	
	@Autowired
	IssueLogExcel issueLogExcel;
	@Autowired
	IssueLogService issueLogService;
	
	@GetMapping(value= "/api/issue")
	public List<IssueLog> issueLogList(IssueLogSearchVO issueLogSearchVO) {
		return issueLogService.issueLogList(issueLogSearchVO);
	}
	
	@GetMapping(value= "/api/issue/excel")
	public void issueExcelDown(IssueLogSearchVO issueLogSearchVO, HttpServletResponse response) throws IOException {
		List<IssueLog> issueLogList = issueLogService.issueLogList(issueLogSearchVO);
		
		File writeExcel = issueLogExcel.writeExcel(issueLogList);
		
		ServletOutputStream outputStream = response.getOutputStream();
		FileUtils.copyFile(writeExcel, outputStream);
		
		outputStream.close();
	}

	@PostMapping(value= "/api/issue")
	public Map<String, Object> applicationCreate(MultipartFile excelFile, Authentication authentication) throws IOException{
		Map<String, Object> map = new HashMap<>();
		String extension = FileNameUtils.getExtension(excelFile.getOriginalFilename());
		
		File createTempFile = File.createTempFile(UUID.randomUUID()+"temp", "."+extension);
		
		excelFile.transferTo(createTempFile);
		
		try {
			issueLogExcel.readExcel(createTempFile);
		} catch (Exception e) {
			e.printStackTrace();
			
			map.put("result", "fail");
			map.put("msg", e.getMessage());
			
			return map;
		}
		
		createTempFile.delete();
		
		map.put("result", "success");
		map.put("msg", "등록 성공");
		
		return map;
	}
	
	@PutMapping(value= "/api/issue")
	public Map<String, Object> applicationUpdate(@RequestBody List<IssueLogVO> issueLog) throws IOException{
		
		Map<String, Object> map = new HashMap<>();
		issueLogService.issueLogUpdate(issueLog);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@DeleteMapping(value= "/api/issue")
	public Map<String, Object> applicationDelete(@RequestBody List<IssueLogVO> issueLog) throws IOException{
		
		Map<String, Object> map = new HashMap<>();
		issueLogService.issueLogDelete(issueLog);
		
		map.put("result", "success");
		map.put("msg", "삭제 성공");
		
		return map;
	}
}
