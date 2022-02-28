package com.nik.hpm.customer.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.customer.repository.CustomerRepository;
import com.nik.hpm.customer.vo.CustomerSearchVO;
import com.nik.hpm.enumcode.Yn;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer customer(Customer customer) {
		//Optional<Customer> findById = customerRepository.findById(customer.getId());
		//return findById.get();
		return customerRepository.customer(customer);
	}
	
	public Page<Customer> customerList(CustomerSearchVO customerSearchVO, Pageable pageable){
		return customerRepository.customerList(customerSearchVO, pageable);
	}
	
	public Map<String, Object> customerModify(Customer customer) {
		Map<String, Object> map = new HashMap<>();
		Optional<Customer> findById = customerRepository.findById(customer.getId());
		Customer findByName = customerRepository.findByName(customer.getName());
		if(findByName != null && findById.get().getId() != findByName.getId()) {
			map.put("result", "fail");
			map.put("msg", "이미 존재하는 거래처입니다.");
			return map;
		}else {
			
			findById.ifPresent(cus->{
				cus.setName(customer.getName());
				cus.setRepName(customer.getRepName());
				cus.setCompanyRegNumber(customer.getCompanyRegNumber());
				cus.setBizCondition(customer.getBizCondition());
				cus.setItem(customer.getItem());
				cus.setTel(customer.getTel());
				cus.setMobile(customer.getMobile());
				cus.setEmail(customer.getEmail());
				cus.setFax(customer.getFax());
				cus.setPicName(customer.getPicName());
				cus.setPicTel(customer.getPicTel());
				cus.setPostNumber(customer.getPostNumber());
				cus.setAdress(customer.getAdress());
				cus.setAddressJibun(customer.getAddressJibun());
				cus.setAdressDetail(customer.getAdressDetail());
				cus.setEtc(customer.getEtc());
				cus.setBillPicName(customer.getBillPicName());
				cus.setBillPicTel(customer.getBillPicTel());
				cus.setBillPicMail(customer.getBillPicMail());
			});
			
			map.put("result", "success");
			map.put("msg", "등록성공");
			return map;
		}
		
	}
	
	public Map<String, Object> customerCreate(Customer customer) {
		Map<String, Object> map = new HashMap<>();
		
		if(customerRepository.findByName(customer.getName()) != null) {
			map.put("result", "fail");
			map.put("msg", "이미 등록된 거래처입니다.");
			return map;
		}else {
			customer.setDelYn(Yn.N);
			customerRepository.save(customer);
			map.put("result", "success");
			map.put("msg", "등록성공");
			return map;
		}
	}
	
	public void customerDelete(Customer customer) {
		Optional<Customer> findById = customerRepository.findById(customer.getId());
		findById.ifPresent(cus->{
			LocalDateTime now = LocalDateTime.now();
			cus.setName(cus.getName()+"_$del_"+now);
			cus.setDelYn(Yn.Y);
		});
		
	}
}
