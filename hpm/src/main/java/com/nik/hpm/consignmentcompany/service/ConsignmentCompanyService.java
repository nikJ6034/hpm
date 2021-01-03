package com.nik.hpm.consignmentcompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.hpm.code.DelYn;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.consignmentcompany.repository.ConsignmentCompanyRepository;
import com.nik.hpm.consignmentcompany.vo.ConsignmentCompanySearchVO;

@Service
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
		Optional<ConsignmentCompany> findById = consignmentCompanyRepository.findById(consignmentCompany.getId());
		
		findById.ifPresent(com->{
			com.setTel(consignmentCompany.getTel());
			com.setFax(consignmentCompany.getFax());
			com.setPostNumber(consignmentCompany.getPostNumber());
			com.setAdress(consignmentCompany.getAdress());
			com.setAdressDetail(consignmentCompany.getAdressDetail());
			com.setEtc(consignmentCompany.getEtc());
		});
		
	}
	
	public void companyCreate(ConsignmentCompany consignmentCompany) {
		consignmentCompany.setDelYn(DelYn.N);
		consignmentCompanyRepository.save(consignmentCompany);
	}
	
	public void companyDelete(ConsignmentCompany consignmentCompany) {
		Optional<ConsignmentCompany> findById = consignmentCompanyRepository.findById(consignmentCompany.getId());
		findById.ifPresent(com->{
			com.setDelYn(DelYn.Y);
		});
		
	}
}
