package com.tweet.app.model;

import java.sql.Date;

public class User {
	private String firstName;
	private String lastName;
	private Date dob;
	private String email;
	private String password;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		 this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		 this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		 this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		 this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		 this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", email=" + email
				+ ", password=" + password + "]";
	}
	public User(String firstName, String lastName, Date dob, String email, String password) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.dob = dob;
	this.email = email;
	this.password = password;
	
}
	public User() {
		// TODO Auto-generated constructor stub
	}

	
	

}
