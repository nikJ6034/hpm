package com.nik.hpm.web.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.nik.hpm.config.oauth2.OAuthToken;
import com.nik.hpm.member.entity.Member;

@RestController
public class LoginController {
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/login/success")
	public Map<String, Object> loginSuccess(Authentication authentication){
		Map<String, Object> map = new HashMap<>();
		System.out.println(authentication.getClass().getName());
		String securityMember = (String)authentication.getPrincipal();
		map.put("memberId", securityMember);
		map.put("result", "success");
		
		return map;
	}
	
	@GetMapping("/login/fail")
	public Map<String, Object> loginFail(){
		Map<String, Object> map = new HashMap<>();
		
		map.put("result", "fail");
		
		return map;
	}
	
	@PostMapping(value = "/oauth2/callback")
	public OAuthToken callbackSocial(@RequestBody Member member) {
		String credentials = "testClientId:testSecret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        
        params.add("username", member.getMemberId());
        params.add("password", member.getMemberPassword());
        params.add("grant_type", "password");
        params.add("redirect_uri", "http://localhost:8080/oauth2/callback");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        Gson gson = new Gson();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/oauth/token", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
        	System.out.println(response.getBody());
            return gson.fromJson(response.getBody(), OAuthToken.class);
        }
        return null;
	}
	
	@PostMapping("/oauth2/token/refresh")
	public OAuthToken refreshToken(@RequestBody OAuthToken token) {

        String credentials = "testClientId:testSecret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refresh_token", token.getRefresh_token());
        params.add("grant_type", "refresh_token");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/oauth/token", request, String.class);
        Gson gson = new Gson();
        if (response.getStatusCode() == HttpStatus.OK) {
            return gson.fromJson(response.getBody(), OAuthToken.class);
        }
        return null;
}
}
