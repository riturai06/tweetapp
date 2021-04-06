package com.tweeter.app.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tweet.app.model.User;
import com.tweet.app.service.TwitterService;

public class TweetDao {
	static Connection Conn = null;
	static PreparedStatement PrepareStat = null;
	String response = null;
	static User user= new User();
	static boolean isLogin= false;
	static TwitterService service= new TwitterService();
	static Scanner sc= new Scanner(System.in);
	int row;

	public int addDataToDB(User user) throws ClassNotFoundException, FileNotFoundException, IOException, ParseException{

		try {	
			String insertQueryStatement = "INSERT  INTO  user(firstName, lastName, dob, email, password) VALUES(?,?,?,?,?)";
			Connection Conn = ConnectionFactory.getConnection();
			PrepareStat = Conn.prepareStatement(insertQueryStatement);
			PrepareStat.setString(1, user.getFirstName());
			PrepareStat.setString(2, user.getLastName());
			PrepareStat.setDate(3, user.getDob());
			PrepareStat.setString(4, user.getEmail());
			PrepareStat.setString(5, user.getPassword());
			int row= PrepareStat.executeUpdate();
			if(row>0) {
				System.out.println("********* " +user.getFirstName()+" added successfully*************");
			}
		} catch (

				SQLException e) {
			e.printStackTrace();
		}
		return row;

	}

	public Boolean login(String email, String password) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		Conn= ConnectionFactory.getConnection();
		Boolean loginStatus=false;
		if(user.equals(user.getEmail())&& password.equals(user.getPassword())) {
			System.out.println("Welcome "+ user.getFirstName());}

		try {isLogin=true;
		PreparedStatement ps = Conn.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			loginStatus=true;
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setEmail(rs.getString("email"));
			user.setDob( rs.getDate("dob") );
			System.out.print("Welcome " +user.getFirstName() +" ");
			System.out.println(user.getLastName());
			System.out.println(user.getEmail());
			System.out.println(user.getDob());
			System.out.println("Want to reset password??");
			String response=sc.next();
			if(response.equals("yes")) {
				user.setEmail(rs.getString("email"));
				user.getEmail();
				System.out.println("Enter old password");
				password= user.getPassword();
				String oldPassword= sc.next();
				System.out.println("Enter new password :");
				String newPassword= sc.next();
				forgetPassword(email, newPassword);

			}
		}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}  
		return loginStatus;

	}



	public static void forgetPassword(String email, String password) throws ClassNotFoundException, FileNotFoundException, IOException {
		Conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = Conn.prepareStatement("SELECT * FROM user WHERE email=?");
			ps.setString(1, email);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				PreparedStatement pc = Conn.prepareStatement("UPDATE user SET password=? WHERE email=?");
				pc.setString(1, password);
				pc.setString(2, email);
				pc.executeUpdate();
				System.out.println("***********************password set succesfully*********************");
			}} catch (SQLException ex) {
				ex.printStackTrace();
			}


	}


	public static void postNewTweets(String tweet, String email) throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
		Connection Conns= ConnectionFactory.getConnection();
		email=user.getEmail();
		PreparedStatement PrepareStat = Conns.prepareStatement("INSERT  INTO  tweets(tweet,email) VALUES(?,?)");
		PrepareStat.setString(1, tweet);
		PrepareStat.setString(2, email);
		PrepareStat.executeUpdate();

	}



	public static List<String> searchTweetByUserId(String email) throws ClassNotFoundException, FileNotFoundException, IOException {
		List<String> tweets = new ArrayList<String>(); 
		Conn= ConnectionFactory.getConnection();
		if(isLogin==true) {
			try {

				// MySQL Select Query Tutorial

				String getQueryStatement = "SELECT * FROM tweets WHERE email='"+email+"'";
				PrepareStat = Conn.prepareStatement(getQueryStatement);
				// Execute the Query, and get a java ResultSet
				ResultSet rs = PrepareStat.executeQuery(getQueryStatement);

				// Let's iterate through the java ResultSet
				while (rs.next()) {
					tweets.add(rs.getString("tweet"));
				}
				return tweets;
			} catch (

					SQLException e) {
				e.printStackTrace();
			}}

		return tweets;
	}







	public static Boolean forget(String email, String password) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		Conn= ConnectionFactory.getConnection();
		Boolean loginStatus=false;
		if(user.equals(user.getEmail())&& password.equals(user.getPassword())) {
			System.out.println("Welcome "+ user.getFirstName());}

		try {isLogin=true;
		PreparedStatement ps = Conn.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			loginStatus=true;
			user.setEmail(rs.getString("email"));
			user.getEmail();				
			password= user.getPassword();				
			System.out.println("Enter new password :");
			String newPassword= sc.next();
			forgetPassword(email, newPassword);

		}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}  
		return loginStatus;

	}

}
