package com.nt.mapper;

import org.springframework.stereotype.Component;

import com.nt.dto.UserRequestDTO;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.User;

@Component
public class UserMapper {

	public User toEntity(UserRequestDTO dto) {
		
		return  new User(dto.getId(), dto.getName(), dto.getUsername() ,dto.getEmail(), dto.getPassowrd(), dto.getAddress(), dto.getPhone(), dto.isVerified());
	}

	public UserResponseDTO toResponseDTO(User dto) {
		return  new UserResponseDTO(dto.getId(), dto.getName(), dto.getEmail(),dto.getUsername(), dto.getAddress(), dto.getPhone(), dto.isVerified(), null);
	}

	
	
	
	
	
}
