package com.nik.hpm.customer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.customer.repository.CustomerRepository;
import com.nik.hpm.customer.vo.CustomerSearchVO;
import com.nik.hpm.enumcode.DelYn;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer customer(Customer customer) {
		Optional<Customer> findById = customerRepository.findById(customer.getId());
		
		return findById.get();
	}
	
	public Page<Customer> customerList(CustomerSearchVO customerSearchVO, Pageable pageable){
		return customerRepository.customerList(customerSearchVO, pageable);
	}
	
	public void customerModify(Customer customer) {
		Optional<Customer> findById = customerRepository.findById(customer.getId());
		
		findById.ifPresent(cus->{
			cus.setRepName(customer.getRepName());
			cus.setCompanyRegNumber(customer.getCompanyRegNumber());
			cus.setBizCondition(customer.getBizCondition());
			cus.setItem(customer.getItem());
			cus.setTel(customer.getTel());
			cus.setFax(customer.getFax());
			cus.setPicName(customer.getPicName());
			cus.setPostNumber(customer.getPostNumber());
			cus.setAdress(customer.getAdress());
			cus.setAdressDetail(customer.getAdressDetail());
			cus.setEtc(customer.getEtc());
		});
		
	}
	
	public void customerCreate(Customer customer) {
		customer.setDelYn(DelYn.N);
		customerRepository.save(customer);
	}
	
	public void customerDelete(Customer customer) {
		Optional<Customer> findById = customerRepository.findById(customer.getId());
		findById.ifPresent(cus->{
			cus.setDelYn(DelYn.Y);
		});
		
	}
}
