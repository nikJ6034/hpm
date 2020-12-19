package com.nik.hpm.config.security;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nik.hpm.member.entity.Member;
import com.nik.hpm.member.entity.MemberRolegroup;
import com.nik.hpm.member.repository.MemberRepository;
import com.nik.hpm.role.entity.Rolegroup;
import com.nik.hpm.role.entity.RolegroupRole;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> member = memberRepository.findByMemberId(username);
		return member.map(m-> {
			
			Set<GrantedAuthority> grantedAuthority = new HashSet<>();
			List<MemberRolegroup> memberRolegroupList = m.getMemberRolegroupList();
			memberRolegroupList.forEach(roleGroup ->{
				Rolegroup rolegroup2 = roleGroup.getRolegroup();
				List<RolegroupRole> roleGroupRoleList = rolegroup2.getRoleGroupRoleList();
				
				roleGroupRoleList.forEach(roles ->{
					grantedAuthority.add(new SimpleGrantedAuthority(roles.getRole().getRoleName()))
					;
				});
			});
			
			SecurityMember securityMember = new SecurityMember(m, grantedAuthority);
			return securityMember;
		}).get();
		
	}
}
