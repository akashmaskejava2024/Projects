package com.rt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rt.entity.RegisterEntity;
@Repository
public class RegisterDao {


	@Autowired
	JdbcTemplate Template;
	public boolean signUp(RegisterEntity register) {
		try{
			Object[] args={register.getFirstName(),register.getLastName(),register.getEmail(),register.getContactNumber(),register.getUsername(),register.getPassword(),register.getConfirmPassword()};
			int signup=Template.update("INSERT INTO librarian(`FirstName`,`LastName`,`Email`,`ContactNumber`,`Username`,`Password`,`ConfirmPassword`)values(?,?,?,?,?,?,?)",
					args);
		
				if(signup==1)
				{
					return true;
				}
			}catch(Exception e)
			{
				System.out.println(e);
			}
			return false;
			
			}
	}

