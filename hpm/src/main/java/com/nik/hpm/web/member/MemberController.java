package com.nik.hpm.web.member;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.member.entity.Member;
import com.nik.hpm.member.service.MemberService;
import com.nik.hpm.member.vo.MemberPasswordChangeVO;
import com.nik.hpm.member.vo.MemberSearchVO;
import com.nik.hpm.member.vo.MemberVO;

@RestController
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@GetMapping(value= "/api/members")
	public 	Page<Member> findMemberList(MemberSearchVO memberSearchVO, @PageableDefault(size = 10)Pageable pageable){
		Page<Member> memberList = memberService.memberList(memberSearchVO, pageable);
		return memberList;
	}
	
	@PostMapping(value= "/api/members")
	public Map<String, Object> memberCreate(@RequestBody Member member){
		Map<String, Object> map = new HashMap<>();
		memberService.memberCreate(member);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@DeleteMapping(value= "/api/members")
	public Map<String, Object> memberDelete(@RequestBody Member member){
		Map<String, Object> map = new HashMap<>();
		memberService.memberDelete(member);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@PutMapping(value= "/api/members")
	public Map<String, Object> memberModify(@RequestBody MemberVO memberVO){
		Map<String, Object> map = new HashMap<>();
		memberService.memberModify(memberVO);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
	
	@PutMapping(value= "/api/members/changePassword")
	public Map<String, Object> memberChangePassword(@Valid @RequestBody MemberPasswordChangeVO memberPasswordChangeVO){
		Map<String, Object> map = new HashMap<>();
		memberService.passwordChange(memberPasswordChangeVO);
		
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}
}
