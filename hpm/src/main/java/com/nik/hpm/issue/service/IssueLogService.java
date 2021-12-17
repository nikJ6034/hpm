package com.nik.hpm.issue.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.hpm.issue.entity.IssueLog;
import com.nik.hpm.issue.repository.IssueLogRepository;
import com.nik.hpm.issue.vo.IssueLogSearchVO;
import com.nik.hpm.issue.vo.IssueLogVO;

@Service
public class IssueLogService {

	@Autowired
	IssueLogRepository issueLogRepository;
	
	public List<IssueLog> issueLogList(IssueLogSearchVO issueLogSearchVO) {
		return issueLogRepository.issueLogList(issueLogSearchVO);
	}
	
	public void issueLogUpdate(List<IssueLogVO> issueLogList) {
		ModelMapper modelMapper = new ModelMapper();
		issueLogList.forEach(item ->{
			IssueLog map = modelMapper.map(item, IssueLog.class);
			issueLogRepository.save(map);
			
		});
		
	}

	public void issueLogDelete(List<IssueLogVO> issueLogList) {
		ModelMapper modelMapper = new ModelMapper();
		issueLogList.forEach(item ->{
			IssueLog map = modelMapper.map(item, IssueLog.class);
			issueLogRepository.delete(map);
			
		});
	}
}
