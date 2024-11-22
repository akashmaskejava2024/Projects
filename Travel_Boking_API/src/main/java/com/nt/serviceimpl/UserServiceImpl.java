package com.nt.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Service;

import com.nt.dto.UserRequestDTO;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.User;
import com.nt.mapper.UserMapper;
import com.nt.repository.UserRepository;
import com.nt.service.JWTService;
import com.nt.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authManager;
	
	
	@Autowired
	private JWTService jwtService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptVersion.$2A, 12);

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserResponseDTO addNewUser(UserRequestDTO dto) {

		User user = userMapper.toEntity(dto);
		user.setPassowrd(encoder.encode(user.getPassowrd()));

		User addedUser = userRepository.save(user);

		if (addedUser != null) {

			return userMapper.toResponseDTO(addedUser);

		}
		return null;

	}

	@Override
	public String validateUser(User user) {

		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername()	, user.getPassowrd())); 
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}
		
		return "Failed";
	}

}
