package com.nik.hpm.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryDsl{
	Optional<Member> findByMemberIdAndDelYn(String memberId, Yn yn);
}
