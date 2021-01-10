package com.nik.hpm.code.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.hpm.code.vo.CommonCodeSearchVO;
import com.nik.hpm.code.vo.CommonCodeVO;

public interface CommonCodeRepositoryDsl {

	public Page<CommonCodeVO> codeList(CommonCodeSearchVO commonCodeSearchVO, Pageable pageable);
}
