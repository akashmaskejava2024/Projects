package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.EmailVerificationToken;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken	, Integer > {

	EmailVerificationToken findByToken(String token);


	EmailVerificationToken findByUser_id(int id);

}
