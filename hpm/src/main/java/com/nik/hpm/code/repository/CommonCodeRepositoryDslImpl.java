package com.nik.hpm.code.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.code.entity.CommonCode;
import com.nik.hpm.code.entity.QCommonCode;
import com.nik.hpm.code.vo.CommonCodeSearchVO;
import com.nik.hpm.code.vo.CommonCodeVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;

public class CommonCodeRepositoryDslImpl extends QuerydslRepositorySupport implements CommonCodeRepositoryDsl {

	public CommonCodeRepositoryDslImpl() {
		super(CommonCode.class);
	}

	@Override
	public Page<CommonCodeVO> codeList(CommonCodeSearchVO commonCodeSearchVO, Pageable pageable) {
		QCommonCode commoncode = QCommonCode.commonCode;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(StringUtils.isNotBlank(commonCodeSearchVO.getCondition())) {
			
			if(commonCodeSearchVO.getCondition().equals("name") && StringUtils.isNotBlank(commonCodeSearchVO.getKeyword())) {
				builder.and(commoncode.codeName.contains(commonCodeSearchVO.getKeyword()));
			}
			
		}
		
		QueryResults<CommonCodeVO> fetchResults = from(commoncode)
		.where(builder,commoncode.pid.isNull())
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.select(Projections.bean(CommonCodeVO.class, commoncode.id, commoncode.code, commoncode.codeName, commoncode.codeDesc, commoncode.pid.id.as("pid")))
		.fetchResults();
		
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}
	

	@Override
	public CommonCode code(CommonCode cmmon) {
		QCommonCode commonCode = new QCommonCode("code1");
		QCommonCode commonCode2 = new QCommonCode("code2");
		
		return from(commonCode)
		.join(commonCode2)
		.on(commonCode.eq(commonCode2.pid))
		.where(commonCode.code.eq(cmmon.getCode()))
		.fetchOne();
	}

}
