package com.nik.hpm.consignmentcompany.vo;

import com.nik.hpm.consignmentcompany.entity.ConsignmentCompany;
import com.nik.hpm.enumcode.Yn;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConsignmentCompanyVO {
	
	public ConsignmentCompanyVO() {}
	
	public ConsignmentCompanyVO(ConsignmentCompany consignmentCompany) {
		this.id = consignmentCompany.getId();
		this.name = consignmentCompany.getName();
		this.tel = consignmentCompany.getTel();
		this.fax = consignmentCompany.getFax();
		this.postNumber = consignmentCompany.getPostNumber();
		this.adress = consignmentCompany.getAdress();
		this.addressJibun = consignmentCompany.getAddressJibun();
		this.adressDetail = consignmentCompany.getAdressDetail();
		this.etc = consignmentCompany.getEtc();
		this.picName = consignmentCompany.getPicName();
		this.picTel = consignmentCompany.getPicTel();
		this.hpmPicName = consignmentCompany.getHpmPicName();
		this.delYn = consignmentCompany.getDelYn();
	}

	private long id;
	
	private String name;
	
	private String tel;
	
	private String fax;
	
	private Integer postNumber;
	
	private String adress;
	
	private String addressJibun;
	
	private String adressDetail;
	
	private String etc;
	
	/** 담당자 */
	private String picName;
	
	/** 담당자 전화번호 */
	private String picTel;
	
	/** HPM 담당자 */
	private String hpmPicName;
	
	private Yn delYn;
}
