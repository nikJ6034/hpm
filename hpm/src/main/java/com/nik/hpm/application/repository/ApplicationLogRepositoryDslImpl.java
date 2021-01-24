package com.nik.hpm.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.application.entity.ApplicationLog;
import com.nik.hpm.application.entity.QApplication;
import com.nik.hpm.application.entity.QApplicationLog;
import com.nik.hpm.application.vo.ApplicationLogSearchVO;

public class ApplicationLogRepositoryDslImpl extends QuerydslRepositorySupport implements ApplicationLogRepositoryDsl {

	public ApplicationLogRepositoryDslImpl() {
		super(ApplicationLog.class);
	}
	
	@Override
	public List<ApplicationLog> applicationLogList(ApplicationLogSearchVO applicationLogSearchVO) {
		QApplicationLog qApplicationLog = QApplicationLog.applicationLog;
		QApplication application = QApplication.application;
		
		return from(qApplicationLog)
				.innerJoin(application)
				.on(application.eq(qApplicationLog.application))
				.where(application.appliRegDate.between(applicationLogSearchVO.getStartDt(), applicationLogSearchVO.getEndDt())
						, qApplicationLog.consignmentCompany.eq(applicationLogSearchVO.getConsignmentCompany()))
				.fetch();
				
	}


}
