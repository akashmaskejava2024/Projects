package com.nt.event;

import java.io.Serializable;

import org.springframework.context.ApplicationEvent;

import com.nt.entity.User;

public class UserRegistrationCompleteEvent  extends ApplicationEvent implements Serializable{

    private static final long serialVersionUID = 1L; // Explicit serialVersionUID field

	
	private final User user;
	private final String applicationURL;
	
	public UserRegistrationCompleteEvent(User user, String applicationURL) {
		super(user);
		this.user = user;
		this.applicationURL = applicationURL;
	}

	public User getUser() {
		return user;
	}

	public String getApplicationURL() {
		return applicationURL;
	}
	
	
}
