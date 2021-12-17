package com.nik.hpm.issue.repository;

import java.util.List;

import com.nik.hpm.issue.entity.IssueLog;
import com.nik.hpm.issue.vo.IssueLogSearchVO;

public interface IssueLogRepositoryDsl {

	public List<IssueLog> issueLogList(IssueLogSearchVO issueLogSearchVO);
}
