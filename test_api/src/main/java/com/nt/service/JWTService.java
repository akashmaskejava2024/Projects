package com.nt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JWTService {

	String getUsernameByToken(String token);

	String generateJwtToken(String username);

	boolean validateToken(String token, UserDetails userDetails);

}
