
package com.nt.service;


import org.springframework.stereotype.Service;

import com.nt.dto.UserRequestDTO;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.User;

@Service
public interface UserService {

	User addNewUser(UserRequestDTO reqDto);

	boolean checkIfAlreadyExists(UserRequestDTO reqDto);

	User checkIfExistsByEmail(String email);

	String validateLoginUser(String username, String password);

	
}
