package com.tweet.app;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tweet.app.exceptions.TweetNotFoundExceptions;
import com.tweet.app.model.Tweets;
import com.tweet.app.model.User;
import com.tweet.app.service.Registration;
import com.tweet.app.service.TwitterService;
import com.tweeter.app.dao.ConnectionFactory;
import com.tweeter.app.dao.TweetDao;

public class App{

	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException, TweetNotFoundExceptions, ParseException {

		Scanner scan = new Scanner(System.in);
		Registration registration= new Registration();
		User user= new User();
		Tweets tweets= new Tweets();
		Boolean isLogin= false;
		String email = null;


		//Creating Menu
		while(true){
			if(isLogin==false) {
				System.out.println("To Register, Enter 1");
				System.out.println("Login, Enter 2");
				System.out.println("Forgot Password, Enter 3 ");
				System.out.println("Enter Choice :");
				int ch= scan.nextInt();
				System.out.println("*****************************************************************");


				//Boolean isLoginStatusStatusStatus=UserLogin.login(user.getEmail(),user.getPassword());


				switch(ch){
				case 1: System.out.println("Registration Enter your Details");
				ConnectionFactory.getConnection();
				registration.addDataToDB(user);
				break;

				case 2: System.out.println("Please Enter your credentials");
				isLogin= TwitterService.login(user.getEmail(),user.getPassword());

				break;
				case 3: System.out.println("Please follow the below steps to set ur password:");
				System.out.println("please enter your email id:");
				email=user.getEmail();
				email= scan.next();
				System.out.println("Enter old password");
				String oldPassword= scan.next();
				TweetDao.forget(email, oldPassword);
				break;
				}
			}

			while(true) {
				if(isLogin.equals(true)) {
					System.out.println("Add Tweet: Enter 4");
					System.out.println("Search tweet by userId: Enter 5");
					System.out.println("View all user: Enter 6");
					System.out.println("For Logout: Enter 7");
					System.out.println("Enter Choice :");

					Integer chh= scan.nextInt();
					switch(chh){
					case 4 : System.out.println("Post Tweet");
					email= user.getEmail();
					TwitterService.postNewTweets(tweets.getTweet(),email);
					break;
					case 5 : 
						List<String> tweetss = new ArrayList<String>();
						tweetss = TwitterService.searchTweetByUserId(email);
						if(tweetss.isEmpty()) {
							throw new TweetNotFoundExceptions("this user has no tweets");
						}
						System.out.println(tweetss);
						break;
					case 6: List<User> allUsers = new ArrayList<User>();
					allUsers= TwitterService.viewAllUsers();
					System.out.println(allUsers);
					break;
					case 7: TwitterService.logout();
					System.exit(0);
					break;
					default: System.out.println("Incorrect input!!! Please re-enter choice from our menu");
					break;
					}}

				break;


			}
		}



	}
}







