package com.tweet.app.model;

public class Tweets {
	
	private String email;
	private String tweet;
	User user;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Tweets(String email, String tweet, User user) {
		super();
		this.email = email;
		this.tweet = tweet;
		this.user = user;
	}
	public Tweets() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Tweets [email=" + email + ", tweet=" + tweet + ", user=" + user + "]";
	}

	

}
