package com.tweeter.app.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	public static final String DRIVER = "jdbc.driver";
	public static final String URL = "jdbc.url";
	public static final String USER = "jdbc.username";
	public static final String PASS = "jdbc.password";
	private static Connection connection= null;
	private static Properties properties= null;

	/**
	 * Get a connection to database
	 * @return Connection object
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static Connection getConnection() throws ClassNotFoundException, FileNotFoundException, IOException
	{
		try {
			properties= new Properties();
			properties.load(new FileInputStream("C:\\Mavens\\tweeter\\src\\main\\java\\com\\tweeter\\app\\dao\\db.properties"));
			Class.forName(properties.getProperty(DRIVER));
			connection=DriverManager.getConnection(properties.getProperty(URL),properties.getProperty(USER),properties.getProperty(PASS));
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
		return connection;
	}


	public static void main(String[] args) throws Throwable {
	}


}
