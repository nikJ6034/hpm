package com.nik.hpm.customer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.customer.vo.CustomerSearchVO;

public interface CustomerRepositoryDsl {

	public Page<Customer> customerList(CustomerSearchVO customerSearchVO, Pageable pageable);
	
	public Customer customer(Customer customer);
}
