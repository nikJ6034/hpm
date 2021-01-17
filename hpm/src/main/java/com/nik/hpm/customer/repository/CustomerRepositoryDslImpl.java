package com.nik.hpm.customer.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.code.entity.QCommonCode;
import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.customer.enitity.QCustomer;
import com.nik.hpm.customer.vo.CustomerSearchVO;
import com.nik.hpm.enumcode.Yn;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

public class CustomerRepositoryDslImpl extends QuerydslRepositorySupport implements CustomerRepositoryDsl {

	public CustomerRepositoryDslImpl() {
		super(Customer.class);
	}

	@Override
	public Page<Customer> customerList(CustomerSearchVO customerSearchVO, Pageable pageable) {
		QCustomer customer = QCustomer.customer;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(StringUtils.isNotBlank(customerSearchVO.getKeyword())) {
			
			if("name".equals(customerSearchVO.getCondition()) && StringUtils.isNotBlank(customerSearchVO.getKeyword())) {
				builder.and(customer.name.contains(customerSearchVO.getKeyword()));
			}else if("tel".equals(customerSearchVO.getCondition()) && StringUtils.isNotBlank(customerSearchVO.getKeyword())) {
				builder.and(customer.tel.contains(customerSearchVO.getKeyword()));
			}else if("companyRegNumber".equals(customerSearchVO.getCondition()) && StringUtils.isNotBlank(customerSearchVO.getKeyword())) {
				builder.and(customer.companyRegNumber.contains(customerSearchVO.getKeyword()));
			}else {
				builder.or(customer.name.contains(customerSearchVO.getKeyword()))
				.or(customer.tel.contains(customerSearchVO.getKeyword()))
				.or(customer.companyRegNumber.contains(customerSearchVO.getKeyword()));
			}
			
		}
		
		QueryResults<Customer> fetchResults = from(customer)
		.where(customer.delYn.eq(Yn.N), builder)
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.fetchResults();
		
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}

	@Override
	public Customer customer(Customer cus) {
		
		QCustomer customer = QCustomer.customer;
		QCommonCode commonCode = new QCommonCode("code1");
		QCommonCode commonCode2 = new QCommonCode("code2");
		
		JPQLQuery<Customer> where2 = from(customer)
		.select(Projections.fields(Customer.class, ExpressionUtils.as(
				JPAExpressions
					.select(commonCode2.codeDesc)
					.from(commonCode)
					.join(commonCode2)
					.on(commonCode.eq(commonCode2.pid))
					.where(commonCode.code.eq("bizCondition")
						.and(commonCode2.code.eq(customer.bizCondition))
				)
				, "bizCondition")
				,customer.id
				,customer.name
				,customer.adressDetail
				,customer.adress
				,customer.companyRegNumber
				,customer.etc
				,customer.fax
				,customer.item
				,customer.picName
				,customer.postNumber
				,customer.repName
				,customer.tel))
		.where(customer.id.eq(cus.getId()))
		;
		
		return where2.fetchOne();
	}
	
	

}
