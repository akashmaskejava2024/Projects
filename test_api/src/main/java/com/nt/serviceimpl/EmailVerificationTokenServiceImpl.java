package com.nt.serviceimpl;

import java.util.Calendar;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nt.entity.EmailVerificationToken;
import com.nt.entity.User;
import com.nt.event.UserRegistrationCompleteEvent;
import com.nt.repository.EmailVerificationTokenRepository;
import com.nt.repository.UserRepository;
import com.nt.service.EmailVerificationTokenService;


@Service
public class EmailVerificationTokenServiceImpl implements EmailVerificationTokenService {

	@Autowired
	private EmailVerificationTokenRepository emailVerificationTokenRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public EmailVerificationToken saveEmailVerficationToken(EmailVerificationToken emailVerificationToken) {
		
		return emailVerificationTokenRepository.save(emailVerificationToken);	
	}




	@Override
	public void sendVerificationMail(String email, String uRL) throws MessagingException {
	
		    MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setTo(email);
	        helper.setSubject("Please confirm your email address");
	        helper.setText("<html><body><p>Click the link below to confirm your registration:</p>" +
	                "<p><a href='" + uRL + "'>Confirm my account</a></p></body></html>", true);
	        javaMailSender.send(message);
		
	}




	@Override
	public String validateToken(String token) {
		
		EmailVerificationToken dbToken = emailVerificationTokenRepository.findByToken(token);
		
		if(dbToken == null) {
			return "invalid";
		}
		
		User user = dbToken.getUser();
		
		if(dbToken.getExpirationTime().getTime() - Calendar.getInstance().getTime().getTime() <= 0) {
			emailVerificationTokenRepository.delete(dbToken);
			return "Token Expired";
		}
		
		user.setEnabled(true);
		userRepository.save(user);
		return "valid";
	}




	@Override
	public EmailVerificationToken generateNewVerificationToken(User user, String applicationURL) {
				
		
		EmailVerificationToken token = emailVerificationTokenRepository.findByUser_id(user.getId());
		String newToken = UUID.randomUUID().toString();
		if(token != null) {
			token.setToken(newToken);
			EmailVerificationToken addedToken=   emailVerificationTokenRepository.save(token);
			return addedToken;
			
		} else {
			EmailVerificationToken newlyAddedToken = emailVerificationTokenRepository.save(new EmailVerificationToken(user, newToken));
			return newlyAddedToken;		
		}
		
		
		
	}

}
