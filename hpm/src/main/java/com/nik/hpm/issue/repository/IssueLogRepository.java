package com.nik.hpm.issue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.issue.entity.IssueLog;

public interface IssueLogRepository extends JpaRepository<IssueLog, Long>, IssueLogRepositoryDsl{

}
