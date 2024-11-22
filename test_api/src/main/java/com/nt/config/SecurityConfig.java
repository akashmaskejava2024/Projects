package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mysql.cj.callback.UsernameCallback;
import com.nt.filter.JWTFilter;
import com.nt.service.UserPrincipalService;

@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	private JWTFilter jwtFilter;
	
	
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(); // Or another encoder of your choice
	    }
	
	
	@Autowired
	private UserPrincipalService userPrincipalService;
	

	private static final String[] WHITE_LIST_URLS = { "/user", "/user/login", "/user/verifyRegistration",
			"/user/resendVerificationlink" };

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		http.cors()
//		.and()
//		.csrf()
//		.disable()
//		.authorizeHttpRequests()
//		.antMatchers(WHITE_LABEL_URLS)
//		.permitAll()
//	
		return http.cors().and().csrf().disable().authorizeRequests().antMatchers(WHITE_LIST_URLS) // Using
																									// antMatchers() for
																									// URL patterns
				.permitAll() // Allow public access to these URLs
				.anyRequest().authenticated() // All other requests need to be authenticated
				.and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before
																								// the
																								// UsernamePasswordAuthenticationFilter
				.build();
	}
//
//	@Bean
//	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//		 // Use HttpSecurity to build the AuthenticationManager
//        AuthenticationManagerBuilder authenticationManagerBuilder = 
//            http.getSharedObject(AuthenticationManagerBuilder.class);
//        
//        // You can add your custom user details service and password encoder here
//        authenticationManagerBuilder.userDetailsService(userPrincipalService)
//            .passwordEncoder(new BCryptPasswordEncoder());
//
//        return authenticationManagerBuilder.build();
//	}

	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	 
	
}
