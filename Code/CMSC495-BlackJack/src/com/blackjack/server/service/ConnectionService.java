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
	 * Gets the MySQL connection for the app with no database identifier
	 * @return the connection for dev mode if in dev mode, or the connection
	 * for production if in production.
	 */
	public static Connection getNoDBConnection() {
		Connection connection = null;
		if (isDevelopmentMode()) {
			try {
				connection = (Connection)DriverManager.getConnection(
						devConnectionNoDB, devuserName, devpassword);
			} catch(SQLException e) {
				//TODO log message appropriately
				System.out.println("Error initializing NoDBConnection: " + e.getMessage());
			}
		} else {

		}
		return connection;
	}
	
	/**
	 * Gets the connection to the app with the database identifier
	 * @return the connection for the database in dev mode if in dev mode, or the connection
	 * for the production database if in production.
	 */
	public static Connection getConnection() {
		Logger.getLogger(ConnectionService.class.getName()).log(Level.INFO, "Connecting to DB");
		Connection connection = null;
		if (isDevelopmentMode()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = (Connection)DriverManager.getConnection(
						devConnection, devuserName, devpassword);
			} catch(ClassNotFoundException | SQLException e) {
				Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, e.getMessage() + "/nConnection: " + devConnection);
				System.out.println("Error initializing connection: " + e.getMessage());
			}
		} else {
			try {
				connection = (Connection)DriverManager.getConnection(prodConnection);
			} catch (SQLException e) {
				Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, e.getMessage() + "/nConnection: " + prodConnection);
				System.out.println("Error initializing Connection.");
			}		
		}
		return connection;
	}
	
	/**
	 * Creates the database with the specified name
	 * @param name the name of the database to create
	 */
	public static void createDB(String name) {
		Connection conn = getNoDBConnection();
		Statement s;
		
		try {
			s = conn.createStatement();
		} catch (SQLException e) {
			//TODO log message appropriately
			System.err.println("An error occurred connection to MySQL");
			return;
		}
		
		try {
			s.executeUpdate("CREATE DATABASE " + name);
		} catch (SQLException e) {
			//TODO log message appropriately
			System.err.println("An error occurred creating the database with name " + devDBName);
		}
	}
	
	/**
	 * Creates the schema for the current database.
	 */
	public static void createSchema() {
		Connection conn = getConnection();
		Statement s;
		try {
			s = conn.createStatement();
		} catch (SQLException e) {
			//TODO log message appropriately
			System.err.println("An error occurred connection to MySQL");
			return;
		}
		try {
			s.executeUpdate(createSchemaScript());
		} catch (SQLException e) {
			//TODO log message appropriately
			System.err.println("An error occurred creating the schema for database with name " + devDBName);
		}
	}
	
	/**
	 * 
	 * @return true if in development mode, false if in production
	 */
	public static boolean isDevelopmentMode() {
		return true;
//		return !GWT.isProdMode() && GWT.isClient();
	}
	
	/**
	 * Reads the schema creation script and returns a Java string
	 * representation of the script to run in jdbc.
	 * @return the String representation of the schema creation script.
	 */
	private static final String createSchemaScript() {
		StringBuilder script = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(pathToSchemaScript));
			String line = "";
			while((line = br.readLine()) != null) {
				script.append(line);
				script.append("/n/r");
			}
			br.close();
		} catch (IOException e) {
			// TODO log message appropriately
			System.err.println("An error occurred reading the file at " + pathToSchemaScript);
		}
		return script.toString();
	}
	
	/**
	 * Checks if the db exists.
	 * @param dbName the name of the db to check.
	 * @return true if the db exists in the current MySQL server, false if otherwise
	 * @throws SQLException thrown if there was an error connecting to the SQL service
	 */
	private static boolean DBExists(String dbName) throws SQLException {
		Connection con = getNoDBConnection();
		ResultSet rs = null;
		try {
			rs = con.getMetaData().getCatalogs();
			while(rs.next()) {
				String catalogs = rs.getString(1);
				if (dbName.equals(catalogs)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Failed to connect to MySQL DB");
		} finally {
			if (rs != null) {
				rs.close();
			}
			con.close();
		}
		return false;
	}
}
