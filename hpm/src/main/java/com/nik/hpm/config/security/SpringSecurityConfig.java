package com.nik.hpm.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nik.hpm.config.security.filter.CustomUsernamePasswordAutthenticationFilter;
import com.nik.hpm.config.security.handler.RestAuthenticationFailureHandler;
import com.nik.hpm.config.security.handler.RestAuthenticationSuccessHandler;


//@EnableWebSecurity
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private CustomAuthenticationProvider authProvider;
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
    }
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic().disable()
		.csrf().disable()
		.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		.formLogin().disable()
		.cors().configurationSource(corsConfigurationSource()).and()
		.authorizeRequests()
			.antMatchers("/login","/login/success").permitAll()
		.and()
		.logout().disable()
		;
	}
	
   @Bean
   public CorsConfigurationSource corsConfigurationSource() {
	CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:8081");
       configuration.addAllowedMethod("*");
       configuration.addAllowedHeader("*");
       configuration.setAllowCredentials(true);
       configuration.setMaxAge(3600L);
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", configuration);
       return source;
   }
	
   @Bean
   public RestAuthenticationSuccessHandler authenticationSuccessHandler(){
       return new RestAuthenticationSuccessHandler();
   }
   
   @Bean
   public RestAuthenticationFailureHandler authenticationFailureHandler(){
       return new RestAuthenticationFailureHandler();
   }
	
   protected CustomUsernamePasswordAutthenticationFilter getAuthenticationFilter() {
	   CustomUsernamePasswordAutthenticationFilter authFilter = new CustomUsernamePasswordAutthenticationFilter();
	   try {
		   authFilter.setFilterProcessesUrl("/user/login");
		   authFilter.setAuthenticationManager(this.authenticationManagerBean());
		   authFilter.setUsernameParameter("username");
		   authFilter.setPasswordParameter("password");
		   authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		   authFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
	   }catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   return authFilter;
   }
}
