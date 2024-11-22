package com.nt.serviceimpl;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserMapper mapper;

	private PasswordEncoder encoder = new BCryptPasswordEncoder(BCryptVersion.$2A, 12);

	@Override
	public User addNewUser(UserRequestDTO reqDto) {

		User user = mapper.toEntity(reqDto);
		user.setPassword(encoder.encode(reqDto.getPassword()));

		User addedUser = userRepository.save(user);
		if (addedUser != null) {
			return addedUser;
		}

		return null;
	}

	@Override
	public boolean checkIfAlreadyExists(UserRequestDTO reqDto) {

		return userRepository.existsByEmail(reqDto.getEmail());

	}

	@Override
	public User checkIfExistsByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public String validateLoginUser(String username, String password) {
		
		
		try {
            // Authenticate username and password first 
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
					if(authentication.isAuthenticated()) {
						//generate JWT token 
						String token = jwtService.generateJwtToken(username);
						System.out.println(token);
						return token;
					}
			
			
		} catch (Throwable e) {
			e.printStackTrace();
			return "Failed";
		}
		
        return "Failed";

		
//		User user = userRepository.findByUsername(username);
//		if(user != null) {
//            
//			if(encoder.matches(password, user.getPassword())) {
//				if(user.isEnabled()) {
//					return "valid";
//				}
//				return "verify mail first";
//			} else {
//				return "Invalid Password";
//			}
//			
//			
//		}
//		
//		return "Invalid Credinetials";
	}

}
