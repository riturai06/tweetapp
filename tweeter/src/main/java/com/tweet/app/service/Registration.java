package com.tweet.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.tweet.app.model.User;
import com.tweeter.app.dao.TweetDao;

import java.sql.Date;


public class Registration {

	static Connection Conn = null;
	static PreparedStatement PrepareStat = null;
	Boolean result;
	String email;
	SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
	static User user= new User();
	Scanner sc =new Scanner(System.in);

	public void addDataToDB(User user) throws ClassNotFoundException, FileNotFoundException, IOException, ParseException{
		System.out.println("Inserting records into db");


		System.out.println("Enter First Name");		
		String firstName= user.getFirstName();
		firstName=sc.nextLine();
		System.out.println("Enter Last Name");		
		String lname= user.getLastName();
		lname=sc.nextLine();
		System.out.println("Enter DOB");
		DateFormat datePattern= new SimpleDateFormat("DD-MM-yyyy");
		java.util.Date date= datePattern.parse(sc.next());
		java.sql.Date dob= new java.sql.Date(date.getTime());
		//Date dob= user.getDob();


		do {
			System.out.println("Enter Email");
			email = user.getEmail();
			email=sc.next();
			String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
			result = email.matches(regex);
			if(!result) {
				System.out.println("Given email-id is invalid");
				System.out.println("Please enter valid email Id");
			}
		}while(!result);
		System.out.println("Enter Password");
		String password= user.getPassword();
		password=sc.next();
		user = new User(firstName, lname, dob, email, password);
		TweetDao dao= new TweetDao();
		int row= dao.addDataToDB(user);


		// execute insert SQL statement

		 
		
	}

	private void log(String string) {
		// TODO Auto-generated method stub
		
	}





	}