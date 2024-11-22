package com.nt.mapper;

import org.springframework.stereotype.Component;

import com.nt.dto.UserRequestDTO;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.User;

@Component
public class UserMapper {

	public UserResponseDTO toResponseDTO(User user) {
		
		return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getUsername(), null, user.isEnabled());
		
	}
	
	
	public User	 toEntity(UserRequestDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getUsername(), dto.getPassword(), dto.isEnabled());
		
	}
	
	
}
