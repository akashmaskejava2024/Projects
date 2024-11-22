package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserRequestDTO {

	private int id;
	private String name;
	private String email;
	private String username;

	private String passowrd;
	private String Address;
	private String Phone;
	private boolean isVerified;

	
	
}
