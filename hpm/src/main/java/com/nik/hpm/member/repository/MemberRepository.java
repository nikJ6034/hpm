package com.nik.hpm.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByMemberId(String memberId);
}
