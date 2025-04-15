package com.rt.entity;

public class RegisterEntity {
	private String FirstName;
    private String LastName;
    private String ContactNumber;
    private String Email;
    private String Username;
    private String Password;
    private String ConfirmPassword;
    
    public RegisterEntity(){}
    
	public RegisterEntity(String firstName, String lastName, String contactNumber, String email, String username,
			String password, String confirmPassword) {
		super();
		FirstName = firstName;
		this.LastName = lastName;
		ContactNumber = contactNumber;
		Email = email;
		Username = username;
		Password = password;
		ConfirmPassword = confirmPassword;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		this.LastName = lastName;
	}
	public String getContactNumber() {
		return ContactNumber;
	}
	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getConfirmPassword() {
		return ConfirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}
    
   
    
	
}