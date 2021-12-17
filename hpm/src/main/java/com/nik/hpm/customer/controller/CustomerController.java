package com.nik.hpm.customer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.customer.service.CustomerService;
import com.nik.hpm.customer.vo.CustomerSearchVO;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping(value= "/api/customer/{id}")
	public Customer findCustomer(Customer customer) {
		return customerService.customer(customer);
	}

	@GetMapping(value= "/api/customer")
	public 	Page<Customer> findCustomerList(CustomerSearchVO customerSearchVO, @PageableDefault(size = 10)Pageable pageable){
		Page<Customer> customerList = customerService.customerList(customerSearchVO, pageable);
		return customerList;
	}
	
	@PostMapping(value= "/api/customer")
	public Map<String, Object> customerCreate(@RequestBody Customer customer){
		Map<String, Object> map = new HashMap<>();
		customerService.customerCreate(customer);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@DeleteMapping(value= "/api/customer")
	public Map<String, Object> customerDelete(@RequestBody Customer customer){
		Map<String, Object> map = new HashMap<>();
		customerService.customerDelete(customer);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@PutMapping(value= "/api/customer")
	public Map<String, Object> customerModify(@RequestBody Customer customer){
		Map<String, Object> map = new HashMap<>();
		customerService.customerModify(customer);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
}
