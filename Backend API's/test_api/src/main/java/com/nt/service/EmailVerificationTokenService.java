package com.nt.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.nt.entity.EmailVerificationToken;
import com.nt.entity.User;


@Service
public interface EmailVerificationTokenService {


	EmailVerificationToken saveEmailVerficationToken(EmailVerificationToken emailVerificationToken);

	void sendVerificationMail(String email, String uRL) throws MessagingException;

	String validateToken(String token);


	EmailVerificationToken generateNewVerificationToken(User user, String applicationURL);

}
