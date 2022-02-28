package com.nik.hpm.customer.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
import com.nik.hpm.util.file.excel.data.DataAppliLogExcel;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	DataAppliLogExcel dataAppliLogExcel;
	
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
		Map<String, Object> customerCreate = customerService.customerCreate(customer);
		
		return customerCreate;
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
		Map<String, Object> customerModify = customerService.customerModify(customer);
		
		return customerModify;
	}
	
	@GetMapping(value= "/api/excel/customer/delivery/{id}")
	public void appliLogExcel(Customer customer, HttpServletResponse response) throws IOException{
		
		customer = customerService.customer(customer);
		
		File writeExcel = dataAppliLogExcel.writeExcelDelivery(customer);
		
		ServletOutputStream outputStream = response.getOutputStream();
		FileUtils.copyFile(writeExcel, outputStream);
		
		outputStream.close();
	}
	
}
