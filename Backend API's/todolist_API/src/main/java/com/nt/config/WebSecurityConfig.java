 package com.nt.config;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig{

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	  private static final String[] WHITE_LIST_URLS = {
	            "/user",
	            "/user/login",
	            "/user/verifyRegistration*",
	            "/user/resendVerificationlink*"
	    };




	// To Allow particular links to be executed


	  @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	      return  http
	                .cors()
	                .and()
	                .csrf()
	                .disable()
	                .authorizeHttpRequests()
	                .antMatchers(WHITE_LIST_URLS).permitAll()
	                .build();
	    }


	
	
	
	
}
