package com.nik.hpm.customer.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.code.DelYn;
import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.customer.enitity.QCustomer;
import com.nik.hpm.customer.vo.CustomerSearchVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;

public class CustomerRepositoryDslImpl extends QuerydslRepositorySupport implements CustomerRepositoryDsl {

	public CustomerRepositoryDslImpl() {
		super(Customer.class);
	}

	@Override
	public Page<Customer> customerList(CustomerSearchVO customerSearchVO, Pageable pageable) {
		QCustomer customer = QCustomer.customer;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(StringUtils.isNotBlank(customerSearchVO.getCondition())) {
			
			if(customerSearchVO.getCondition().equals("name") && StringUtils.isNotBlank(customerSearchVO.getKeyword())) {
				builder.and(customer.name.contains(customerSearchVO.getKeyword()));
			}else if(customerSearchVO.getCondition().equals("picName") && StringUtils.isNotBlank(customerSearchVO.getKeyword())) {
				builder.and(customer.picName.contains(customerSearchVO.getKeyword()));
			}
			
		}
		
		QueryResults<Customer> fetchResults = from(customer)
		.where(customer.delYn.eq(DelYn.N), builder)
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.fetchResults();
		
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}

}