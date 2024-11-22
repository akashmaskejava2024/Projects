package com.nt.filter;

import java.applet.AppletContext;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nt.entity.UserPrincipal;
import com.nt.service.JWTService;
import com.nt.serviceimpl.UserPrincipalServiceImpl;


@Component
public class JWTFilter extends OncePerRequestFilter{
	
	
	@Autowired
	private JWTService jwtService;

	@Autowired
	private ApplicationContext context;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.getUsernameByToken(token);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details from the database or in-memory store

			UserDetails  userDetails = context.getBean(UserPrincipalServiceImpl.class).loadUserByUsername(username);
			
			
			if(jwtService.validateToken(token, userDetails)) {
				
				// if Valid set authentication context authenticated
				UsernamePasswordAuthenticationToken authTOken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

				authTOken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
				
				SecurityContextHolder.getContext().setAuthentication(authTOken);
			}
				
		}
		
		filterChain.doFilter(request, response);
	}

}
