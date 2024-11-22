package com.nt.cntrl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.RespnseEntityDTO;
import com.nt.dto.UserRequestDTO;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.User;
import com.nt.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    

    @PostMapping
    public ResponseEntity<RespnseEntityDTO> addUser(@Valid @RequestBody UserRequestDTO dto, HttpServletRequest request) {
        try {
        	
        	
            UserResponseDTO resDTO = userService.addNewUser(dto);
            
            
            if(resDTO != null) {
                return new ResponseEntity<>(new RespnseEntityDTO("Success", resDTO), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new  RespnseEntityDTO("Error: User could not be added", null), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // Log the exception here
            return new ResponseEntity<>(new RespnseEntityDTO("Error: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
    
    @RequestMapping("/login")
    public  String login(@RequestBody User user) {
    	
    	return userService.validateUser(user);
		
	}
}
