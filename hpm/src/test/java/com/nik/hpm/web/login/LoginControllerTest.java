package com.nik.hpm.web.login;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

class LoginControllerTest {

	@Test
	void test() {
		PasswordEncoder createDelegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println(createDelegatingPasswordEncoder.encode("1234"));
	}

}
