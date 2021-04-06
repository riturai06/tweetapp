package com.tweet.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tweet.app.model.Tweets;
import com.tweet.app.model.User;
import com.tweeter.app.dao.ConnectionFactory;
import com.tweeter.app.dao.TweetDao;

public class TwitterService {
	static User user= new User();
	static Tweets tweets= new Tweets();
	static TweetDao dao= new TweetDao();

	static PreparedStatement PrepareStat=null;
	User users;
	static Boolean isLogin= false;
	static Connection Conn=null;
	static Scanner sc = new Scanner(System.in);
	public static Boolean login(String email, String password) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		Conn= ConnectionFactory.getConnection();
		System.out.print("Enter username : ");
		email=sc.next();
		System.out.print("Enter Password : ");
		password=sc.next();

		return isLogin= dao.login(email,password);


	}

	public static void postNewTweets(String tweet, String email) throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {

		if(isLogin==true) {  
			System.out.println("Add Tweet");
			tweet= tweets.getTweet();
			tweet=sc.nextLine(); 	    
			TweetDao.postNewTweets(tweet, user.getEmail());
		}}

	public static List<String> searchTweetByUserId(String email) throws ClassNotFoundException, FileNotFoundException, IOException {
		System.out.println("Enter email Id:");
		email= sc.next();
		System.out.println("****************Tweets of " +email+"*********************");
		return TweetDao.searchTweetByUserId(email);
	}


	public static void logout() throws ClassNotFoundException, FileNotFoundException, IOException {
		Conn = ConnectionFactory.getConnection();
		System.out.println("user successfully logged out");
	}

	public static List<User> viewAllUsers() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
		Conn= ConnectionFactory.getConnection();
		List<User> users= new ArrayList<User>();

		PreparedStatement ps = Conn.prepareStatement("SELECT * from user");	
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			users.add(new User(rs.getString("firstName"),rs.getString("lastName"),rs.getDate("dob"), rs.getString("email"), null));

		}
		return users;

	}



}



