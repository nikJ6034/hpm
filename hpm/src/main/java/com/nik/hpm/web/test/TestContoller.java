package com.nik.hpm.web.test;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContoller {
	
	@GetMapping("/test")
	public String test(Authentication authentication) {
		
		
		return authentication.getAuthorities().toString();
	}
}
