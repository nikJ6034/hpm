package com.nik.hpm.web.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.member.entity.Member;
import com.nik.hpm.member.repository.MemberRepository;

@RestController
public class MemberController {
	
	@Autowired
	MemberRepository memberRepository;

	@GetMapping(value = "/api/users")
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }
}
