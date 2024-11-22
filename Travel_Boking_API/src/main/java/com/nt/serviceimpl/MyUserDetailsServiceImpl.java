package com.nt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.entity.MyUserPrincipals;
import com.nt.entity.User;
import com.nt.repository.UserRepository;
import com.nt.service.MyUserDetailsService;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not Found");
		}
		// now if (user!= null) then return UserDetails object but it is Interface
		
		return new MyUserPrincipals(user);
	}

}
