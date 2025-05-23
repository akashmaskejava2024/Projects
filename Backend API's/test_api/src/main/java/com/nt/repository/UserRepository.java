package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{


	boolean existsByEmail(String email);

	User findByEmail(String email);

	User findByUsername(String username);

}
