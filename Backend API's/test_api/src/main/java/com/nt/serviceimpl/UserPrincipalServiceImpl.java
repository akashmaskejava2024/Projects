package com.nt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.entity.User;
import com.nt.entity.UserPrincipal;
import com.nt.repository.UserRepository;
import com.nt.service.UserPrincipalService;

@Service
public class UserPrincipalServiceImpl  implements UserPrincipalService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User Nt found in the database");
		}
		return new UserPrincipal(user);
	}

}
