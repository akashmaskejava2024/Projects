package com.nt.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	private LocalDate dob;
	private String email;
	private long phone;
	private boolean isVerified;
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Establishing the one-to-many relationship
    private java.util.List<List> lists; // List of lists associated with the user

}
