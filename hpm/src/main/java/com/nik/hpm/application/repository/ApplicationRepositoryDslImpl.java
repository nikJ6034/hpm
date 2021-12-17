package com.nik.hpm.application.repository;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.application.entity.Application;
import com.nik.hpm.application.entity.QApplication;
import com.nik.hpm.application.entity.QApplicationLog;
import com.nik.hpm.application.vo.AppLogAllListVO;
import com.nik.hpm.application.vo.ApplicationSearchVO;
import com.nik.hpm.application.vo.LogAllVO;
import com.nik.hpm.attachfile.entity.QAttachFile;
import com.nik.hpm.consignmentcompany.entity.QConsignmentCompany;
import com.nik.hpm.customer.enitity.QCustomer;
import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.QMember;
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
		QCustomer customer = QCustomer.customer;
		QMember member = QMember.member;
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(application.delYn.eq(Yn.N));
		if(applicationSearchVO.getCustomer() > 0) {
			booleanBuilder.and(application.customer.id.eq(applicationSearchVO.getCustomer()));
		}
		
		QueryResults<Application> fetchResults = from(application)
				.select(Projections.fields(Application.class, 
						 application.id
						, application.requestCustomerName
						, application.requestCustomerAddress
						, application.appliRegDate
						, application.regDate
						, application.customer
						, application.regMember))
				.leftJoin(application.customer, customer)
				.leftJoin(application.regMember, member)
				.where(booleanBuilder)
				.orderBy(application.appliRegDate.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
				
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}
	
	

	@Override
    public List<AppLogAllListVO> applicationAll(ApplicationSearchVO applicationSearchVO) {
	    QApplication application = QApplication.application;
	    QApplicationLog applicationlog = QApplicationLog.applicationLog;
	    QCustomer customer = QCustomer.customer;
		QMember member = QMember.member;
		QConsignmentCompany consignmentCompany = QConsignmentCompany.consignmentCompany;
	    BooleanBuilder builder = new BooleanBuilder();
	    builder.and(application.delYn.eq(Yn.N));
	    
	    builder.and(applicationlog.consignmentCompany.id.notIn(1,2,3));	// hpm을 제외한 나머지 조회
	    
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
            }else if("regNumber".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
                builder.and(applicationlog.regNumber.contains(applicationSearchVO.getKeyword()));
            }else if("model".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
                builder.and(applicationlog.model.contains(applicationSearchVO.getKeyword()));
            }
        }
        
        if(applicationSearchVO.getStartDate() != null && applicationSearchVO.getEndDate() != null) {
			if("appliRegDate".equals(applicationSearchVO.getDateCondition())) {
				builder.and(application.appliRegDate.between(applicationSearchVO.getStartDate(), applicationSearchVO.getEndDate()));
			}else if("publishedDate".equals(applicationSearchVO.getDateCondition())) {
				builder.and(applicationlog.publishedDate.between(applicationSearchVO.getStartDate(), applicationSearchVO.getEndDate()));
			}else if("correctionDate".equals(applicationSearchVO.getDateCondition())) {
				builder.and(applicationlog.correctionDate.between(applicationSearchVO.getStartDate(), applicationSearchVO.getEndDate()));
			}
			
        }
        
        return from(application)
        .select(Projections.fields(AppLogAllListVO.class
                , ExpressionUtils.as(applicationlog.id,"id")
                , ExpressionUtils.as(application.customer.name,"customerName")
                , ExpressionUtils.as(application.customer.picName,"customerPicName")
                , ExpressionUtils.as(application.customer.tel,"customerTel")
                , ExpressionUtils.as(application.customer.fax,"customerFax")
                , ExpressionUtils.as(application.customer.mobile,"customerMobile")
                , ExpressionUtils.as(application.customer.email,"customerEmail")
                , ExpressionUtils.as(application.customer.adress,"customerAddress")
                , ExpressionUtils.as(application.customer.adressDetail,"customerAddressDetail")
                , ExpressionUtils.as(application.customer.billPicName,"billPicName")
                , ExpressionUtils.as(application.customer.billPicTel,"billPicTel")
                , ExpressionUtils.as(application.customer.billPicMail,"billPicMail")
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
                , ExpressionUtils.as(application.deliveryInfo,"deliveryInfo")
                , ExpressionUtils.as(application.regMember.name,"regMemberName")
                , ExpressionUtils.as(application.regDate,"regDate")
                , ExpressionUtils.as(application.technicalManager,"technicalManager")
                , ExpressionUtils.as(application.reportLanguage,"reportLanguage")
                , ExpressionUtils.as(applicationlog.regNumber,"regNumber")
                , ExpressionUtils.as(applicationlog.model,"model")
                , ExpressionUtils.as(applicationlog.resolution,"resolution")
                , ExpressionUtils.as(applicationlog.etc,"etc")
                , ExpressionUtils.as(applicationlog.consignmentCompany.name,"consignmentCompanyName")
                , ExpressionUtils.as(applicationlog.deviceName,"deviceName")
                , ExpressionUtils.as(applicationlog.quantity,"quantity")
                , ExpressionUtils.as(applicationlog.reportNumber,"reportNumber")
                , ExpressionUtils.as(applicationlog.productionCompany,"productionCompany")
                , ExpressionUtils.as(applicationlog.deviceNumber,"deviceNumber")
                , ExpressionUtils.as(applicationlog.standard,"standard")
                , ExpressionUtils.as(applicationlog.unit,"unit")
                , ExpressionUtils.as(applicationlog.correctionDate,"correctionDate")
                , ExpressionUtils.as(applicationlog.correctionFee,"correctionFee")
                , ExpressionUtils.as(applicationlog.place,"place")
                , ExpressionUtils.as(applicationlog.practitioner,"practitioner")
                , ExpressionUtils.as(applicationlog.middleCategory,"middleCategory")
                , ExpressionUtils.as(applicationlog.smallCategory,"smallCategory")
                , ExpressionUtils.as(applicationlog.publishedDate,"publishedDate")
                , ExpressionUtils.as(applicationlog.inspectionType,"inspectionType")
                , ExpressionUtils.as(applicationlog.carryType,"carryType")
                , ExpressionUtils.as(applicationlog.paymentStateType,"paymentStateType")
                , ExpressionUtils.as(applicationlog.consignmentCompany.id,"consignmentCompany")
                ))
        .leftJoin(application.applicationLogList, applicationlog)
        .leftJoin(application.customer, customer)
		.leftJoin(application.regMember, member)
		.leftJoin(applicationlog.consignmentCompany, consignmentCompany)
        .where(builder)
        .orderBy(application.appliRegDate.desc(),applicationlog.regNumber.desc())
        .fetch();
    }

    @Override
	public Application application(Application app) {
		
		QApplication application = QApplication.application;
		QCustomer customer = QCustomer.customer;
		QMember member = QMember.member;
		QAttachFile attachfile = QAttachFile.attachFile;
		QApplicationLog applicationlog = QApplicationLog.applicationLog;
		
		JPQLQuery<Application> where = from(application)
		.leftJoin(application.applicationLogList, applicationlog).fetchJoin()
		.leftJoin(application.customer, customer).fetchJoin()
		.leftJoin(application.regMember, member).fetchJoin()
		.leftJoin(application.customerSignImg, attachfile).fetchJoin()
		
		.where(application.id.eq(app.getId()));
		
		return where.fetchFirst();
	}

	@Override
	public List<LogAllVO> logAll(ApplicationSearchVO applicationSearchVO) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("(select");
		stringBuilder.append(" appLog.id");
		stringBuilder.append(" ,applog.reg_number");
		stringBuilder.append(" ,applog.middle_category");
		stringBuilder.append(" ,applog.report_number");
		stringBuilder.append(" ,app.request_customer_name");
		stringBuilder.append(" ,customer.name as 'customer_name'");
		stringBuilder.append(" ,applog.device_name");
		stringBuilder.append(" ,applog.production_company");
		stringBuilder.append(" ,applog.model");
		stringBuilder.append(" ,applog.device_number");
		stringBuilder.append(" ,applog.standard");
		stringBuilder.append(" ,applog.resolution");
		stringBuilder.append(" ,applog.unit");
		stringBuilder.append(" ,applog.correction_date");
		stringBuilder.append(" ,applog.place");
		stringBuilder.append(" ,applog.practitioner");
		stringBuilder.append(" ,applog.small_category");
		stringBuilder.append(" ,applog.published_date");
		stringBuilder.append(" ,app.technical_manager");
		stringBuilder.append(" ,applog.correction_fee");
		stringBuilder.append(" ,applog.quantity");
		stringBuilder.append(" ,applog.etc");
		stringBuilder.append(" ,customer.pic_name as customer_pic_name");
		stringBuilder.append(" ,customer.mobile as customer_mobile");
		stringBuilder.append(" ,customer.tel as customer_tel");
		stringBuilder.append(" ,customer.fax as customer_fax");
		stringBuilder.append(" ,customer.email as customer_email");
		stringBuilder.append(" ,customer.adress as customer_address");
		stringBuilder.append(" ,customer.adress_detail as customer_address_detail");
		
		stringBuilder.append(" ,customer.rep_name as customer_rep_name");
		stringBuilder.append(" ,customer.company_reg_number as customer_company_reg_number");
		stringBuilder.append(" ,customer.biz_condition as customer_biz_condition");
		stringBuilder.append(" ,customer.item as customer_item");
		stringBuilder.append(" ,customer.post_number as customer_post_number");
		
		stringBuilder.append(" ,applog.payment_state_type");
		stringBuilder.append(" ,customer.bill_Pic_Name as  customer_bill_pic_name");
		stringBuilder.append(" ,customer.Bill_Pic_Tel as  customer_bill_pic_tel");
		stringBuilder.append(" ,customer.Bill_Pic_Mail as  customer_bill_pic_mail");
		stringBuilder.append(" ,cc.name as  consignment_company_name");
		stringBuilder.append(" ,app.APPLI_REG_DATE as appliRegDate");
		stringBuilder.append(" from application app");
		stringBuilder.append(" inner join application_log appLog on (app.id = appLog.application_id)");
		stringBuilder.append(" left outer join customer customer on (app.customer_id = customer.id)");
		stringBuilder.append(" left outer join consignment_company cc on (appLog.consignment_company_id = cc.id)");
		stringBuilder.append(" where 1=1");
		stringBuilder.append(" and app.del_yn = 'N'");
		
		if(applicationSearchVO.getStartDate() != null && applicationSearchVO.getEndDate() != null) {
			stringBuilder.append(" and (app.APPLI_REG_DATE between '" + applicationSearchVO.getStartDate() + "' and '"+ applicationSearchVO.getEndDate()+"')");
        }
		stringBuilder.append(" and appLog.consignment_company_id not in (1,2,3)");
		//builder.and(applicationlog.consignmentCompany.id.notIn(1,2,3));	// hpm을 제외한 나머지 조회
		
		if(StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            
            if("".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and (customer.name like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or applog.device_name like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or applog.production_company like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or applog.reg_number like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or applog.model like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or applog.device_number like '%"+applicationSearchVO.getKeyword()+"%')");
            }else if("customerName".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and customer.name like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("deviceName".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and applog.device_name like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("productionCompany".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and applog.production_company like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("deviceNumber".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and applog.device_number like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("regNumber".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and applog.reg_number like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("model".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and applog.model like '%"+applicationSearchVO.getKeyword()+"%'");
            }
            
        }
		
		stringBuilder.append(" )");
		stringBuilder.append("union all");
		
		stringBuilder.append("(select");
		stringBuilder.append(" issueLog.id");
		stringBuilder.append(" ,issueLog.reg_number");
		stringBuilder.append(" ,issueLog.middle_category");
		stringBuilder.append(" ,issueLog.report_number");
		stringBuilder.append(" ,issueLog.request_customer_name");
		stringBuilder.append(" ,customer.name as 'customer_name'");
		stringBuilder.append(" ,issueLog.device_name");
		stringBuilder.append(" ,issueLog.production_company");
		stringBuilder.append(" ,issueLog.model");
		stringBuilder.append(" ,issueLog.device_number");
		stringBuilder.append(" ,issueLog.standard");
		stringBuilder.append(" ,issueLog.resolution");
		stringBuilder.append(" ,issueLog.unit");
		stringBuilder.append(" ,issueLog.correction_date");
		stringBuilder.append(" ,issueLog.place");
		stringBuilder.append(" ,issueLog.practitioner");
		stringBuilder.append(" ,issueLog.small_category");
		stringBuilder.append(" ,issueLog.published_date");
		stringBuilder.append(" ,issueLog.technical_manager");
		stringBuilder.append(" ,issueLog.correction_fee");
		stringBuilder.append(" ,issueLog.quantity");
		stringBuilder.append(" ,issueLog.etc");
		stringBuilder.append(" ,customer.pic_name as customer_pic_name");
		stringBuilder.append(" ,customer.mobile as customer_mobile");
		stringBuilder.append(" ,customer.tel as customer_tel");
		stringBuilder.append(" ,customer.fax as customer_fax");
		stringBuilder.append(" ,customer.email as customer_email");
		stringBuilder.append(" ,customer.adress as customer_address");
		stringBuilder.append(" ,customer.adress_detail as customer_address_detail");
		
		stringBuilder.append(" ,customer.rep_name as customer_rep_name");
		stringBuilder.append(" ,customer.company_reg_number as customer_company_reg_number");
		stringBuilder.append(" ,customer.biz_condition as customer_biz_condition");
		stringBuilder.append(" ,customer.item as customer_item");
		stringBuilder.append(" ,customer.post_number as customer_post_number");
		
		stringBuilder.append(" ,issueLog.payment_state_type");
		stringBuilder.append(" ,customer.bill_Pic_Name as customer_bill_pic_name");
		stringBuilder.append(" ,customer.Bill_Pic_Tel as customer_bill_pic_tel");
		stringBuilder.append(" ,customer.Bill_Pic_Mail as customer_bill_pic_mail");
		stringBuilder.append(" ,issueLog.HPM_TYPE as consignment_company_name");
		stringBuilder.append(" ,issueLog.APPLI_REG_DATE as  appliRegDate");
		stringBuilder.append(" from issue_log issueLog");
		stringBuilder.append(" left outer join customer customer on (issueLog.customer_id = customer.id)");
		
		
		stringBuilder.append(" where 1=1");
		if(applicationSearchVO.getStartDate() != null && applicationSearchVO.getEndDate() != null) {
			stringBuilder.append(" and (issueLog.APPLI_REG_DATE between '" + applicationSearchVO.getStartDate() + "' and '"+ applicationSearchVO.getEndDate()+"')");
        }
		
		if(StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            
            if("".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and (customer.name like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or issueLog.device_name like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or issueLog.production_company like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or issueLog.reg_number like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or issueLog.model like '%"+applicationSearchVO.getKeyword()+"%'");
            	stringBuilder.append("or issueLog.device_number like '%"+applicationSearchVO.getKeyword()+"%')");
            }else if("customerName".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and customer.name like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("deviceName".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and issueLog.device_name like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("productionCompany".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and issueLog.production_company like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("deviceNumber".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and issueLog.device_number like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("regNumber".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and issueLog.reg_number like '%"+applicationSearchVO.getKeyword()+"%'");
            }else if("model".equals(applicationSearchVO.getCondition()) && StringUtils.isNotBlank(applicationSearchVO.getKeyword())) {
            	stringBuilder.append("and issueLog.model like '%"+applicationSearchVO.getKeyword()+"%'");
            }
            
        }

		stringBuilder.append(" )");
		
		stringBuilder.append(" order by appliRegDate desc, reg_number desc");
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		Query createNativeQuery = getEntityManager().createNativeQuery(stringBuilder.toString());
		return jpaResultMapper.list(createNativeQuery, LogAllVO.class);
		
	}

    

}
