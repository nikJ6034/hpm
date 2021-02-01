package com.nik.hpm.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.entity.QApplication;
import com.nik.hpm.application.entity.QApplicationLog;
import com.nik.hpm.application.vo.AppLogAllListVO;
import com.nik.hpm.application.vo.ApplicationSearchVO;
import com.nik.hpm.enumcode.Yn;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

public class ApplicationRepositoryDslImpl extends QuerydslRepositorySupport implements ApplicationRepositoryDsl {

	public ApplicationRepositoryDslImpl() {
		super(Application.class);
	}

	@Override
	public Page<Application> applicationList(ApplicationSearchVO applicationSearchVO, Pageable pageable) {
		QApplication application = QApplication.application;
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(application.delYn.eq(Yn.N));
		if(applicationSearchVO.getCustomer() > 0) {
			booleanBuilder.and(application.customer.id.eq(applicationSearchVO.getCustomer()));
		}
		
		QueryResults<Application> fetchResults = from(application)
				.where(booleanBuilder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
				
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}
	
	

	@Override
    public List<AppLogAllListVO> applicationAll(ApplicationSearchVO applicationSearchVO) {
	    QApplication application = QApplication.application;
	    QApplicationLog applicationlog = QApplicationLog.applicationLog;
	    BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(application.delYn.eq(Yn.N));
        
        return from(application)
        .select(Projections.fields(AppLogAllListVO.class
                , ExpressionUtils.as(application.id,"id")
                , ExpressionUtils.as(application.customer.name,"customerName")
                , ExpressionUtils.as(application.requestCustomerName,"requestCustomerName")
                , ExpressionUtils.as(application.requestCustomerAddress,"requestCustomerAddress")
                
                , ExpressionUtils.as(application.customerSameYn,"customerSameYn")
                , ExpressionUtils.as(application.fieldCorrectionNeedYn,"fieldCorrectionNeedYn")
                , ExpressionUtils.as(application.recCalibrationDayYn,"recCalibrationDayYn")
                , ExpressionUtils.as(application.appliRegDateType,"appliRegDateType")
                , ExpressionUtils.as(application.appliRegDate,"appliRegDate")
                , ExpressionUtils.as(application.applicant,"applicant")
                , ExpressionUtils.as(application.applicantEmail,"applicantEmail")
                , ExpressionUtils.as(application.consignee,"consignee")
                , ExpressionUtils.as(application.recCalibrationDayYn,"recCalibrationDayYn")
                
                , ExpressionUtils.as(applicationlog.consignmentCompany.name,"consignmentCompanyName")
                ))
        .innerJoin(applicationlog)
        .on(applicationlog.application.eq(application))
        .where(booleanBuilder)
        .fetch();
    }

    @Override
	public Application application(Application app) {
		
		QApplication application = QApplication.application;
		
		JPQLQuery<Application> where = from(application)
		.where(application.id.eq(app.getId()));
		
		return where.fetchOne();
	}


}
