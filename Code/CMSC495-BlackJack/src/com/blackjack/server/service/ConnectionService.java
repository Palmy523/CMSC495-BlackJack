package com.blackjack.server.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.blackjack.client.Config;
import com.blackjack.client.controls.UserController;
import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.core.shared.GWT;

/**
 * The connection service is used for all actions in connecting to
 * and creating the initial database.
 * 
 * @author Dave
 *
 */
public class ConnectionService {

	/**
	 * The name of the development server database
	 */
	private static final String devDBName = "blackjack";
	
	/**
	 * The username for the development server database
	 */
	private static final String devuserName = "21Dev";
	
	/**
	 * The password for the development server database
	 */
	private static final String devpassword = "blackjack";
	
	/**
	 * The connection string to the MySQL server without the database parameter
	 * can be used to run scripts outside of the database.
	 */
	private static final String devConnectionNoDB = "jdbc:mysql://localhost:3306";
	
	private static final String prodConnection = "jdbc:google:mysql://cmsc495-blackjack:us-central1:blackjack-db/blackjack?user=root";
	
	/**
	 * The connection string to the development mode database
	 */
	private static final String devConnection = devConnectionNoDB + "/" + devDBName;
	
	/**
	 * The path to the schema creation script for the database.
	 */
	private static final String pathToSchemaScript = "//scripts//CreateDB.sql";
	
	/**
	 * Gets the connection to the app with the database identifier
	 * @return the connection for the database in dev mode if in dev mode, or the connection
	 * for the production database if in production.
	 */
	public static Connection getConnection() {
		Connection connection = null;
		if (isDevelopmentMode()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = (Connection)DriverManager.getConnection(
						devConnection, devuserName, devpassword);
			} catch(ClassNotFoundException | SQLException e) {
				Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, e.getMessage() + "/nConnection: " + devConnection);
			}
		} else {
			try {
				connection = (Connection)DriverManager.getConnection(prodConnection);
			} catch (SQLException e) {
				Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, e.getMessage() + "/nConnection: " + prodConnection);
			}		
		}
		return connection;
	}
	
	/**
	 * 
	 * @return true if in development mode, false if in production
	 */
	public static boolean isDevelopmentMode() {
		return SystemProperty.environment.value() != SystemProperty.Environment.Value.Production;
	}
}
