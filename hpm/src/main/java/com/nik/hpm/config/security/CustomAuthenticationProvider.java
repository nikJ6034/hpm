package com.nik.hpm.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.stereotype.Component;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	 @Autowired
	 private CustomUserDetailsService customUserDetailsService;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	@Override
	public Authentication authenticate(Authentication authentication) {
		
		String name = authentication.getName();
		String password = (String) authentication.getCredentials();
				
		UserDetails loadUserByUsername = customUserDetailsService.loadUserByUsername(name);
		if(!passwordEncoder.matches(password, loadUserByUsername.getPassword())) {
			throw new BadClientCredentialsException();
		}
		
		return new UsernamePasswordAuthenticationToken(loadUserByUsername, password, loadUserByUsername.getAuthorities());

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
