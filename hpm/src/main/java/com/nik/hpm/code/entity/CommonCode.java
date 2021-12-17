package com.nik.hpm.code.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CommonCode {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** 분류코드 */
	@Column
	private String code;
	
	/** 분류코드 이름 */
	@Column
	private String codeName;
	
	/** 분류코드 설명 */
	@Column
	private String codeDesc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "pid")
	@JsonBackReference
	private CommonCode pid;
	
	@OneToMany(mappedBy = "pid",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<CommonCode> codeList;
	
	/** 수정 유무 */
	@Transient
	private boolean edite;
	
	/** 삭제 구분 */
	@Transient
	private boolean del;
}
