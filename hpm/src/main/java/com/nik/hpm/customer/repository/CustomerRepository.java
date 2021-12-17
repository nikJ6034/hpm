package com.nik.hpm.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.customer.enitity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryDsl{

	public Customer findByName(String name);
}
