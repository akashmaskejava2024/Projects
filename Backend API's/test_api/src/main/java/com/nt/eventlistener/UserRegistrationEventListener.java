package com.nt.eventlistener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.nt.entity.EmailVerificationToken;
import com.nt.entity.User;
import com.nt.event.UserRegistrationCompleteEvent;
import com.nt.repository.EmailVerificationTokenRepository;
import com.nt.service.EmailVerificationTokenService;

@Component
public class UserRegistrationEventListener implements ApplicationListener<UserRegistrationCompleteEvent> {

	@Autowired
	private EmailVerificationTokenService emailVerificationTokenService;

	@Override
	public void onApplicationEvent(UserRegistrationCompleteEvent event) {
		
		
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		
		EmailVerificationToken emailVerificationToken = emailVerificationTokenService.saveEmailVerficationToken(new EmailVerificationToken( user, token));
		
		
	    //generate the email verification URL
		if(emailVerificationToken != null ) {
			
			String email = emailVerificationToken.getUser().getEmail();
			String URL = event.getApplicationURL() + "/user/verifyRegistration?token=" + emailVerificationToken.getToken();
            try {
	             emailVerificationTokenService.sendVerificationMail(email, URL);
	
            } catch (Exception e) {
              e.printStackTrace();
            }
            
		} 
			
		
		
		
		
	}

}
