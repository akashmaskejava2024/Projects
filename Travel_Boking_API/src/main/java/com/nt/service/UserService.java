package com.nt.service;

import org.springframework.stereotype.Service;

import com.nt.dto.UserRequestDTO;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.User;

@Service
public interface UserService {

	UserResponseDTO addNewUser(UserRequestDTO dto);

	String validateUser(User user);

}
