package com.nt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.dto.UserRequestDTO;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.EmailVerificationToken;
import com.nt.entity.User;
import com.nt.event.RegistrationCompleteEvent;
import com.nt.mapper.UserMapper;
import com.nt.repository.EmailVerificationTokenRepository;
import com.nt.repository.UserRepository;
import com.nt.service.UserService;

import java.util.Calendar;

import javax.persistence.EntityManager;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher publisher;
    
    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public User addUser(UserRequestDTO dto) {
        User user = mapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        try {
            // Use saveOrUpdateUser to manage the entity
            User addedUser = repository.save(user);
            if( addedUser != null)
            	{
            	return addedUser;
            	}
            return null;
        } catch (Exception e) {
        	
        	
            e.printStackTrace();
            
            return null;
        }
    }

    @Override
    public boolean checkIfSameuser(UserRequestDTO dto) {
        try {
            return repository.existsByEmailAndUsername(dto.getEmail(), dto.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateUser(UserRequestDTO dto) {
        User user = repository.findByUsername(dto.getUsername());

        if(user!=null) {
        	boolean isValid =  passwordEncoder.matches(dto.getPassword(), user.getPassword());
        	return isValid;
        }
        
        return false;
        
    }

    @Override
    @Transactional
    public void saveEmailverificationToken(User user, String token) {
        user = saveOrUpdateUser(user); // Ensure user is managed before creating token
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken(user, token);
        emailVerificationTokenRepository.save(emailVerificationToken);
    }

    @Transactional
    public User saveOrUpdateUser(User user) {
        return entityManager.merge(user); // Use merge to attach or update the entity
    }

	@Override
	public String verifyRegiastrationwithtoken(String token) {
		
		EmailVerificationToken emailVerificationToken = emailVerificationTokenRepository.findByToken(token);
		
		if(emailVerificationToken == null) {
			return "Invalid";
		}
		
		
		User user = emailVerificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		
		
		if(emailVerificationToken.getExpirationTime().getTime() - cal.getTime().getTime()  <= 0) {
			
			emailVerificationTokenRepository.delete(emailVerificationToken);
			return "token_expired";			
			
		}
		
		
		user.setVerified(true); 
		repository.save(user);
		
		return "valid";
		
		
		
		
		
	}

	@Override
	public String resendVerificationMail(String email, String applicationURL) {
		
		User user = repository.findByEmail(email);
		
		if(user != null) {
			publisher.publishEvent(new RegistrationCompleteEvent(user,applicationURL));
			return "link_sent";
		}
		
		return "invalid_mail";
	}
}
