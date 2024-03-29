package com.nik.hpm.member.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.Member;
import com.nik.hpm.member.repository.MemberRepository;
import com.nik.hpm.member.vo.MemberPasswordChangeVO;
import com.nik.hpm.member.vo.MemberSearchVO;
import com.nik.hpm.member.vo.MemberVO;
import com.nik.hpm.role.entity.Role;

@Service
@Transactional
public class MemberService {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Member member(Member member) {
		Optional<Member> findById = memberRepository.findById(member.getId());
		
		return findById.get();
	}
	
	public Page<Member> memberList(MemberSearchVO memberSearchVO, Pageable pageable){
		return memberRepository.memberList(memberSearchVO, pageable);
	}
	
	public void memberModify(MemberVO memberVO) {
		Optional<Member> findById = memberRepository.findById(memberVO.getId());
		
		findById.ifPresent(mem->{
			mem.setEmail(memberVO.getEmail());
			mem.setMobile(memberVO.getMobile());
			Role role = new Role();
			role.setId(memberVO.getRole().getId());
			mem.setRole(role);
		});
		
	}
	
	public void passwordChange(MemberPasswordChangeVO memberPasswordChangeVO) {
		
		if(memberPasswordChangeVO.getPasswordConfirm().equals(memberPasswordChangeVO.getMemberPassword())) {
			
			Optional<Member> findById = memberRepository.findById(memberPasswordChangeVO.getId());
			findById.ifPresent(mem->{
				mem.setMemberPassword(passwordEncoder.encode(memberPasswordChangeVO.getMemberPassword()));
			});
		}
		
	}

	public void memberCreate(Member member) {
		String encode = passwordEncoder.encode(member.getMemberPassword());
		member.setDelYn(Yn.N);
		member.setMemberPassword(encode);
		memberRepository.save(member);
	}
	
	public void memberDelete(Member member) {
		Optional<Member> findById = memberRepository.findById(member.getId());
		findById.ifPresent(mem->{
			LocalDateTime now = LocalDateTime.now();
			
			mem.setMemberId(mem.getMemberId()+"_$del_"+now);
			
			mem.setDelYn(Yn.Y);
		});
		
	}
	
}
