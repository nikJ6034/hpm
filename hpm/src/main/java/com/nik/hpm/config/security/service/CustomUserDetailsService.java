package com.nik.hpm.config.security.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nik.hpm.config.security.vo.SecurityMember;
import com.nik.hpm.enumcode.Yn;
import com.nik.hpm.member.entity.Member;
import com.nik.hpm.member.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> member = memberRepository.findByMemberIdAndDelYn(username, Yn.N);
		return member.map(m-> {
			
			Set<GrantedAuthority> grantedAuthority = new HashSet<>();
//			List<MemberRole> memberRolegroupList = m.getMemberRoleList();
			grantedAuthority.add(new SimpleGrantedAuthority(m.getRole().getRoleName()));
//			memberRolegroupList.forEach(memRole ->{
//				Role role = memRole.getRole();
//				grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
//			});
			
			SecurityMember securityMember = new SecurityMember(m, grantedAuthority);
			return securityMember;
		}).get();
		
	}
}
