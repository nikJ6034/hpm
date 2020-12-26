package com.nik.hpm.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.hpm.member.entity.Member;
import com.nik.hpm.member.vo.MemberSearchVO;

public interface MemberRepositoryDsl {
	
	public Page<Member> memberList(MemberSearchVO memberSearchVO, Pageable pageable);
}
