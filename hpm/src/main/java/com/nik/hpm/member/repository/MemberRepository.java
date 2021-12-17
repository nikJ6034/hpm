package com.nik.hpm.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryDsl{
	
	@EntityGraph(value = "withRole")
	Optional<Member> findByMemberIdAndDelYn(String memberId, Yn yn);
	
}
