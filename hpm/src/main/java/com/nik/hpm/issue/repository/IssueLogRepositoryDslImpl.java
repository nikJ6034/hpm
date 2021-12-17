package com.nik.hpm.issue.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.customer.enitity.QCustomer;
import com.nik.hpm.issue.entity.IssueLog;
import com.nik.hpm.issue.entity.QIssueLog;
import com.nik.hpm.issue.vo.IssueLogSearchVO;
import com.querydsl.core.BooleanBuilder;

public class IssueLogRepositoryDslImpl extends QuerydslRepositorySupport  implements IssueLogRepositoryDsl{

	public IssueLogRepositoryDslImpl() {
		super(IssueLog.class);
	}

	@Override
	public List<IssueLog> issueLogList(IssueLogSearchVO issueLogSearchVO) {
		
		QIssueLog issuelog = QIssueLog.issueLog;
		QCustomer customer = QCustomer.customer;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(StringUtils.isNotBlank(issueLogSearchVO.getHpmType())) {
			builder.and(issuelog.hpmType.eq(issueLogSearchVO.getHpmType()));
		}
		
		if(StringUtils.isNotBlank(issueLogSearchVO.getKeyword())) {
            
            if("".equals(issueLogSearchVO.getCondition()) && StringUtils.isNotBlank(issueLogSearchVO.getKeyword())) {
                builder.and(issuelog.customer.name.contains(issueLogSearchVO.getKeyword()))
                .or(issuelog.deviceName.contains(issueLogSearchVO.getKeyword()))
                .or(issuelog.productionCompany.contains(issueLogSearchVO.getKeyword()))
                .or(issuelog.deviceNumber.contains(issueLogSearchVO.getKeyword()));
            }else if("customerName".equals(issueLogSearchVO.getCondition()) && StringUtils.isNotBlank(issueLogSearchVO.getKeyword())) {
                builder.and(issuelog.customer.name.contains(issueLogSearchVO.getKeyword()));
            }else if("deviceName".equals(issueLogSearchVO.getCondition()) && StringUtils.isNotBlank(issueLogSearchVO.getKeyword())) {
                builder.and(issuelog.deviceName.contains(issueLogSearchVO.getKeyword()));
            }else if("productionCompany".equals(issueLogSearchVO.getCondition()) && StringUtils.isNotBlank(issueLogSearchVO.getKeyword())) {
                builder.and(issuelog.productionCompany.contains(issueLogSearchVO.getKeyword()));
            }else if("deviceNumber".equals(issueLogSearchVO.getCondition()) && StringUtils.isNotBlank(issueLogSearchVO.getKeyword())) {
                builder.and(issuelog.deviceNumber.contains(issueLogSearchVO.getKeyword()));
            }else if("regNumber".equals(issueLogSearchVO.getCondition()) && StringUtils.isNotBlank(issueLogSearchVO.getKeyword())) {
                builder.and(issuelog.regNumber.contains(issueLogSearchVO.getKeyword()));
            }else if("model".equals(issueLogSearchVO.getCondition()) && StringUtils.isNotBlank(issueLogSearchVO.getKeyword())) {
                builder.and(issuelog.model.contains(issueLogSearchVO.getKeyword()));
            }
            
        }
		
		if(issueLogSearchVO.getStartDate() != null && issueLogSearchVO.getEndDate() != null) {
			if("publishedDate".equals(issueLogSearchVO.getDateCondition())) {
				builder.and(issuelog.publishedDate.between(issueLogSearchVO.getStartDate(), issueLogSearchVO.getEndDate()));
			}else if("excel".equals(issueLogSearchVO.getDateCondition())) {
				builder.and(issuelog.regDt.between(issueLogSearchVO.getStartDate(), issueLogSearchVO.getEndDate()));
			}else if("correctionDate".equals(issueLogSearchVO.getDateCondition())) {
				builder.and(issuelog.correctionDate.between(issueLogSearchVO.getStartDate(), issueLogSearchVO.getEndDate()));
			}else if("appliRegDate".equals(issueLogSearchVO.getDateCondition())) {
				builder.and(issuelog.appliRegDate.between(issueLogSearchVO.getStartDate(), issueLogSearchVO.getEndDate()));
			}
			
        }
		
		return from(issuelog)
		.where(builder)
		.leftJoin(issuelog.customer, customer).fetchJoin()
		.orderBy(issuelog.appliRegDate.desc(),issuelog.regNumber.desc())
		.fetch();
	}

}
