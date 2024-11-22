package com.nt.service;

import org.springframework.stereotype.Service;

import com.nt.dto.UserRequestDTO;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.User;

@Service
public interface UserService {

	User addUser(UserRequestDTO dto);

	boolean checkIfSameuser(UserRequestDTO dto);

	boolean validateUser(UserRequestDTO dto);

	void saveEmailverificationToken(User user, String token);

	String verifyRegiastrationwithtoken(String token);



	String resendVerificationMail(String email, String applicationURL);

	
	
	
	
	
}
