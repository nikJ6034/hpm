package com.nik.hpm.config.security.filter;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUsernamePasswordAutthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private boolean postOnly = true;
	private Map<String, String> jsonRequest;
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if(postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("로그인 요청은"+request.getMethod() +"만 지원합니다." );
		}
		if(request.getHeader("Content-Type").equals(MediaType.APPLICATION_JSON_VALUE)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				this.jsonRequest = mapper.readValue(request.getReader().lines().collect(Collectors.joining()), new TypeReference<Map<String, String>>(){
					
				});
			}catch(IOException e) {
				e.printStackTrace();
				throw new AuthenticationServiceException("Content-type(application/json) 파싱 에러" );
			}
		}
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		log.info("로그인 요청정보" + username + " : "+ password);
		
		if(username == null) {
			username = "";
		}
		if(password == null) {
			password = "";
		}
		username = username.trim();
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		System.out.println(authRequest.isAuthenticated());
		setDetails(request, authRequest);
		
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		String passwordParameter = super.getPasswordParameter();
		if(request.getHeader("Content-Type").equals(MediaType.APPLICATION_JSON_VALUE)) {
			return jsonRequest.get(passwordParameter);
		}
		return request.getParameter(passwordParameter);
	}
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String usernameParameter = super.getUsernameParameter();
		if(request.getHeader("Content-Type").equals(MediaType.APPLICATION_JSON_VALUE)) {
			return jsonRequest.get(usernameParameter);
		}
		return request.getParameter(usernameParameter);
	}
	@Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}
	
}
