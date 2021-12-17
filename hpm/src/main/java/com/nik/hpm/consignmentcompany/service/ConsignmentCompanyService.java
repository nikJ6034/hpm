package com.nik.hpm.consignmentcompany.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.consignmentcompany.repository.ConsignmentCompanyRepository;
import com.nik.hpm.consignmentcompany.vo.ConsignmentCompanySearchVO;
import com.nik.hpm.enumcode.Yn;

@Service
@Transactional
public class ConsignmentCompanyService {

	@Autowired
	ConsignmentCompanyRepository consignmentCompanyRepository;
	
	public ConsignmentCompany company(ConsignmentCompany consignmentCompany) {
		Optional<ConsignmentCompany> findById = consignmentCompanyRepository.findById(consignmentCompany.getId());
		
		return findById.get();
	}
	
	public List<ConsignmentCompany> companyAll(ConsignmentCompany consignmentCompany) {
		List<ConsignmentCompany> companyAll = consignmentCompanyRepository.findByDelYn(consignmentCompany.getDelYn());
		
		return companyAll;
	}
	
	public Page<ConsignmentCompany> companyList(ConsignmentCompanySearchVO consignmentCompanySearchVO, Pageable pageable){
		return consignmentCompanyRepository.companyList(consignmentCompanySearchVO, pageable);
	}
	
	public void companyModify(ConsignmentCompany consignmentCompany) {
		
		consignmentCompanyRepository.save(consignmentCompany);
		
	}
	
	public void companyCreate(ConsignmentCompany consignmentCompany) {
		consignmentCompany.setDelYn(Yn.N);
		consignmentCompanyRepository.save(consignmentCompany);
	}
	
	public void companyDelete(ConsignmentCompany consignmentCompany) {
		Optional<ConsignmentCompany> findById = consignmentCompanyRepository.findById(consignmentCompany.getId());
		findById.ifPresent(com->{
			com.setDelYn(Yn.Y);
		});
		
	}
}
