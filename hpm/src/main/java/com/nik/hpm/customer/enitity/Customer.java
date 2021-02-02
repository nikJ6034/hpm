package com.nik.hpm.customer.enitity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.nik.hpm.enumcode.Yn;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Customer implements Serializable {

	/** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@NotNull
	/** 거래처명 */
	private String name;
	
	@Column
	/** 대표자명 */
	private String repName;
	
	@Column(unique = true)
	/** 사업자번호 */
	private String companyRegNumber;
	
	@Column
	/** 업태 */
	private String  bizCondition;
	
	@Column
	/** 종목 */
	private String item;
	
	@Column
	/** 전화번호 */
	private String tel;
	
	@Column
    /** 연락처 */
    private String mobile;
	
	@Column
    /** 이메일 */
    private String email;
	
	@Column
	/** 팩스번호 */
	private String fax;
	
	@Column
	/** 담당자 */
	private String picName;
	
	@Column
	/** 우편번호 */
	private Integer postNumber;
	
	@Column
	/** 주소 */
	private String adress;
	
	@Column
	/** 상세주소 */
	private String adressDetail;
	
	@Column
	/**  */
	private String etc;
	
	@Enumerated(EnumType.STRING)
	/** 삭제 구분 */
	@Column Yn delYn;
}
