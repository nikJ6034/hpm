package com.nik.hpm.code.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.hpm.code.entity.CommonCode;
import com.nik.hpm.code.vo.CommonCodeSearchVO;
import com.nik.hpm.code.vo.CommonCodeVO;

public interface CommonCodeRepositoryDsl {
	
	public CommonCode findById(long id);

	public Page<CommonCodeVO> codeList(CommonCodeSearchVO commonCodeSearchVO, Pageable pageable);
	
	public CommonCode code(CommonCode commonCode);
	
	CommonCode cCode(CommonCodeSearchVO commonCode);
}
