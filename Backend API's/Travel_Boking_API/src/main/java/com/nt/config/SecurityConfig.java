package com.nt.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nt.filter.JWTFilter;
import com.nt.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JWTFilter jwtFiter;
	
	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(customizer -> customizer.disable());
		// disable the CSRF for now internally it gets Customizer object instead of
		// lambda expression becasue it is functional interface

		http.authorizeHttpRequests(request -> request
				.antMatchers("/register","/login")
				.permitAll()
				.anyRequest().authenticated())
				.oauth2Login(Customizer.withDefaults());
		// EVery request should be authenticated

		http.formLogin(Customizer.withDefaults());
		// allow only default login form given by spring security

		http.httpBasic(Customizer.withDefaults());
		// for Rest API direct link calling from postman login should not be there

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// HTTP sessions will not be created, and there is no session state between
		// requests, authentication is typically done using tokens like JWT
		
		
		http.addFilterBefore(jwtFiter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	
//	
//	@Bean
//	public UserDetailsService getUserDetailsService() {
//		
//		UserDetails user = User
//				.withDefaultPasswordEncoder()
//				.username("Akash")
//				.password("Akash@123")
//				.build()
//		
//		return new InMemoryUserDetailsManager();
//	}
//	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(BCryptVersion.$2A	, 12));
		provider.setUserDetailsService(myUserDetailsService);
		return provider;
		
	}
}