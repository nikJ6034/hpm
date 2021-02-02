package com.nik.hpm.application.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	    BooleanBuilder builder = new BooleanBuilder();
	    builder.and(application.delYn.eq(Yn.N));
        
        if(StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            
            if("".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
                builder.and(application.customer.name.contains(applicationSearchVO.getKeyword()))
                .or(applicationlog.deviceName.contains(applicationSearchVO.getKeyword()))
                .or(applicationlog.productionCompany.contains(applicationSearchVO.getKeyword()))
                .or(applicationlog.deviceNumber.contains(applicationSearchVO.getKeyword()));
            }else if("customerName".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
                builder.and(application.customer.name.contains(applicationSearchVO.getKeyword()));
            }else if("deviceName".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
                builder.and(applicationlog.deviceName.contains(applicationSearchVO.getKeyword()));
            }else if("productionCompany".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
                builder.and(applicationlog.productionCompany.contains(applicationSearchVO.getKeyword()));
            }else if("deviceNumber".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
                builder.and(applicationlog.deviceNumber.contains(applicationSearchVO.getKeyword()));
            }
            
        }
        
        
        return from(application)
        .select(Projections.fields(AppLogAllListVO.class
                , ExpressionUtils.as(application.id,"id")
                , ExpressionUtils.as(application.customer.name,"customerName")
                
                , ExpressionUtils.as(application.customer.picName,"customerPicName")
                , ExpressionUtils.as(application.customer.tel,"customerTel")
                , ExpressionUtils.as(application.customer.mobile,"customerMobile")
                , ExpressionUtils.as(application.customer.email,"customerEmail")
                
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
                , ExpressionUtils.as(application.takeOverDate,"takeOverDate")
                , ExpressionUtils.as(application.takeOverType,"takeOverType")
                , ExpressionUtils.as(application.regMember.name,"regMemberName")
                , ExpressionUtils.as(application.regDate,"regDate")
                
                , ExpressionUtils.as(applicationlog.consignmentCompany.name,"consignmentCompanyName")
                , ExpressionUtils.as(applicationlog.deviceName,"deviceName")
                , ExpressionUtils.as(applicationlog.quantity,"quantity")
                , ExpressionUtils.as(applicationlog.reportNumber,"reportNumber")
                , ExpressionUtils.as(applicationlog.productionCompany,"productionCompany")
                , ExpressionUtils.as(applicationlog.deviceNumber,"deviceNumber")
                , ExpressionUtils.as(applicationlog.standard,"standard")
                , ExpressionUtils.as(applicationlog.unit,"unit")
                , ExpressionUtils.as(applicationlog.correctionDate,"correctionDate")
                , ExpressionUtils.as(applicationlog.place,"place")
                , ExpressionUtils.as(applicationlog.practitioner,"practitioner")
                , ExpressionUtils.as(applicationlog.middleCategory,"middleCategory")
                , ExpressionUtils.as(applicationlog.smallCategory,"smallCategory")
                , ExpressionUtils.as(applicationlog.publishedDate,"publishedDate")
                , ExpressionUtils.as(applicationlog.technicalManager,"technicalManager")
                , ExpressionUtils.as(applicationlog.reportLanguage,"reportLanguage")
                ))
        .innerJoin(applicationlog)
        .on(applicationlog.application.eq(application))
        .where(builder)
        .orderBy(application.regDate.desc())
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
