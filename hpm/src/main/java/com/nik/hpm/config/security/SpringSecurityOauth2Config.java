package com.nik.hpm.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityOauth2Config extends WebSecurityConfigurerAdapter{

	@Autowired
    private CustomAuthenticationProvider authProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeRequests()
			.antMatchers("/oauth/**", "/oauth2/callback", "/oauth2/token/refresh").permitAll()
			.and()
			.formLogin().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.httpBasic();
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
//	@Bean
//	public OAuth2RestTemplate oauth2RestTemplate() {
//	    ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
//	    ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
//	    resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
//	    resourceDetails.setClientId("testClientId");
//	    resourceDetails.setClientSecret("testSecret");
//	    resourceDetails.setGrantType("password");
//	    resourceDetails.setScope(Arrays.asList("read", "write"));
//	    DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
//	    return new OAuth2RestTemplate(resourceDetails, clientContext);
//	}
	
}
