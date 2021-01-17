package com.nik.hpm.member.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.Member;
import com.nik.hpm.member.entity.QMember;
import com.nik.hpm.member.vo.MemberSearchVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;

public class MemberRepositoryDslImpl extends QuerydslRepositorySupport implements MemberRepositoryDsl {

	public MemberRepositoryDslImpl() {
		super(Member.class);
	}

	@Override
	public Page<Member> memberList(MemberSearchVO memberSearchVO, Pageable pageable) {
		
		QMember member = QMember.member;
		BooleanBuilder builder = new BooleanBuilder();
		
		if(StringUtils.isNotBlank(memberSearchVO.getCondition())) {
			
			if(memberSearchVO.getCondition().equals("id") && StringUtils.isNotBlank(memberSearchVO.getKeyword())) {
				builder.and(member.memberId.contains(memberSearchVO.getKeyword()));
			}else if(memberSearchVO.getCondition().equals("name") && StringUtils.isNotBlank(memberSearchVO.getKeyword())){
				builder.and(member.name.contains(memberSearchVO.getKeyword()));
			}
			
		}
		
		QueryResults<Member> fetchResults = from(member)
		.where(member.delYn.eq(Yn.N),builder)
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.fetchResults();
		
		return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
	}

}
