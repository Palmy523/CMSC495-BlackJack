package com.blackjack.server.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.blackjack.server.service.ConnectionService;
import com.blackjack.server.service.MD5EncryptionService;
import com.blackjack.shared.entities.User;

public class UserControllerServer {

	public static final boolean isEasyPlayDefault = true;
	public static final float startChipCount = 250;
	public static final float startMaxChipCount = startChipCount;
	
	/**
	 * Checks the database for proper credentials and returns a User
	 * with proper fields based on the success or failure of the login.
	 * 
	 * @param username the username to check for in the DB
	 * @param password the password of the user
	 * @return a User object if a match was found with the specified username and password, 
	 * false if username doesn't exist or password is invalid.
	 */
	public static User login(String username, String password) {
		password = MD5EncryptionService.encrypt(password);
		
		User user;
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT user_id FROM user WHERE user_name = ? && password = ? ");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.first();
				int userID = rs.getInt(1);
				if (userID > 0) {
					return getUserById(userID);
				}
			}
		} catch(SQLException e) {
			//TODO log exception appropriately
			System.out.println("An error occurred getting the user by ID: "
					+ e.getMessage());
		} finally {
			try {
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * Gets a user from the database with the supplied userID and returns 
	 * the User object.
	 * @param userID the userID of the User to return
	 * @return a User object that matches the data in the database with the supplied ID
	 */
	private static User getUserById(int userID) {
		
		if (!userExists(userID)) {
			return null;
		}
		
		int id = Integer.valueOf(userID);
		User user = new User();
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM user WHERE user_id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.first();
			user.setUserID(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setEmail(rs.getString(3));
			user = populateGameData(user.getUserID(), user);
			return user;
		} catch(SQLException e) {
			//TODO log exception appropriately
			System.out.println("An error occurred getting the user by ID: "
					+ e.getMessage());
		} finally {
			try {
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * Get a user by their email address.
	 * @param email the email address of the user to get
	 * @return the User that matches the email supplied
	 */
	private static User getUserByEmail(String email) {
		if (!emailExists(email)) {
			return null;
		}
		
		User user = new User();
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM user WHERE email = ?;");
			ps.setString(1, email);
			rs = ps.executeQuery();
			rs.first();
			user.setUserID(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setEmail(rs.getString(3));
			return user;
		} catch(SQLException e) {
			//TODO log exception appropriately
			System.out.println("An error occurred getting the user by email: "
					+ e.getMessage());
		} finally {
			try {
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * Gets a user by their username
	 * @param username the username of the User to get
	 * @return the User object populated with info from the db
	 */
	private static User getUserByUserName(String username) {
		if (!userNameExists(username)) {
			return null;
		}
		
		User user = new User();
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM user WHERE user_name = ?;");
			ps.setString(1, username);
			rs = ps.executeQuery();
			rs.first();
			user.setUserID(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setEmail(rs.getString(3));
			return user;
		} catch(SQLException e) {
			//TODO log exception appropriately
			System.out.println("An error occurred getting the user by email: "
					+ e.getMessage());
		} finally {
			try {
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * Gets the game data for the user and sets the fields in the user object
	 * @param userID the user ID of the user to get the game data for
	 * @param user the user to populate the game data with
	 * @return the User with the game data populated
	 */
	private static User populateGameData(int userID, User user) {
		if (!(user.getUserID() > 0)) {
			return user;
		}
		
		if (!userExists(user.getUserID())) {
			return user;
		}
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM game_data WHERE user_id = ?");
			ps.setInt(1, user.getUserID());
			rs = ps.executeQuery();
			rs.first();
			user.setEasyPlay(rs.getBoolean(1));
			user.setMaxChips(rs.getFloat(2));
			user.setBankAmount(rs.getFloat(3));
			return user;
		} catch(SQLException e) {
			//TODO log exception appropriately
			System.out.println("An error occurred getting the game data for the user with username: " 
					+ user.getUsername()
					+ e.getMessage());
		} finally {
			try {
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return user;
	}
	
	public static User createAccount(String username, String password, String email) {
		if (userNameExists(username)) {
			return null;
		}
		
		if (emailExists(email)) {
			return null;
		}
		
		password = MD5EncryptionService.encrypt(password);

		User user = null;
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO user "
					+ "(user_name, email, password)  " 
					+ "VALUES(?, ?, ?); ");
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			if (ps.executeUpdate() > 0) {
				ps.close();
				user = getUserByUserName(username);
				ps = conn.prepareStatement("INSERT INTO game_data (easy_play, max_chips, bank_amt, user_id) " 
						+ "VALUES (?, ?, ?, ?);");
				ps.setBoolean(1, isEasyPlayDefault);
				ps.setFloat(2, startMaxChipCount);
				ps.setFloat(3, startChipCount);
				ps.setInt(4, user.getUserID());
				if (ps.executeUpdate() > 0) {
					user.setEasyPlay(isEasyPlayDefault);
					user.setMaxChips(startMaxChipCount);
					user.setBankAmount(startChipCount);
				}
			}
			
		} catch (SQLException e) {
			// TODO Log message appropriately
			System.err.println(e.getMessage());
		} finally {
			try {			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		
		return user;
	}
	
	public static float updateChipCount(String userID, int amount) {
		//TODO update the database with the new amount using controller
		
		//TODO return the amount if update was successful, else return the old amount
		// or return -1 if any db actions were unsuccessful
		return -1;
	}
	
	/**
	 * Creates a random password and updates the database with the new password
	 * @param emailAddress the email address of the user to perform the update on
	 * @return the new password if the update was successful, else null
	 */
	public static String resetPassword(String emailAddress) {
		String newPassword = createRandomKey();
		String newPasswordEncrypted = MD5EncryptionService.encrypt(createRandomKey());

		if (!emailExists(emailAddress)) {
			return null;
		}
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE user "
					+ "password = ?"
					+ "WHERE email = ?;");
			ps.setString(1, newPasswordEncrypted);
			ps.setString(2, emailAddress);
			if (ps.executeUpdate() > 0) {
				return newPassword;
			}
		} catch (SQLException e) {
			// TODO Log message appropriately
			System.err.println(e.getMessage());
		} finally {
			try {			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		
		return null;
	}
	
	/**
	 * Checks to see if the user exists in the database.
	 * @param userID the userID of the user to check for.
	 * @return true if the user exists, false if otherwise.
	 */
	public static boolean userExists(int userID) {
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT COUNT(*) > 0 FROM user WHERE user_id = ?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			rs.first();
			int value = rs.getInt(1);
			return value > 0;
		} catch (SQLException e) {
			//TODO log exception appropriately
			System.out.println("An error occurred checking if the user exists in the database: "
					+ e.getMessage());
		} finally {
			try {
				
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return false;
	}
	
	public static boolean userExists(String userID) {
		return userExists(Integer.valueOf(userID));
	}
	
	/**
	 * Checks to see if the username exists in the database
	 * @param username the username to check for
	 * @return true if the username exists false if not
	 */
	public static boolean userNameExists(String username) {
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT COUNT(*) > 0 FROM user WHERE user_name = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			rs.first();
			int value = (int)rs.getLong(1);
			return value > 0;
		} catch (SQLException e) {
			//TODO log exception appropriately
			System.out.println("An error occurred checking if the username exists in the database: "
					+ e.getMessage());
		} finally {
			try {
				
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * Checks if the emailAddress exists in the database
	 * @param emailAddress the email address to check for
	 * @return true if the email address already exists, false if otherwise
	 */
	public static boolean emailExists(String emailAddress) {
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT COUNT(*) > 0 FROM user WHERE email = ?");
			ps.setString(1, emailAddress);
			rs = ps.executeQuery();
			rs.first();
			int value = rs.getInt(1);
			return value > 0;
		} catch (SQLException e) {
			//TODO log exception appropriately
			System.out.println("An error occurred checking if the email exists in the database: "
					+ e.getMessage());
		} finally {
			try {
				
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return false;
	}	
	
	/**
	 * Creates a temporary email update key in the DB
	 * @param userID the userID of the account to create the key for 
	 * to update
	 * @return the confirmationKey that was created to confim the update
	 */
	public static String createTemporaryEmailUpdateKey(String userID) {
		//TODO create a key
		//TODO store in database
		//TODO return the created key
		return null;
	}
	
	public static boolean createTemporaryEmail(String userID, String email) {
		//TODO set the tempEmail for the userID in the DB
		//TODO return success or not
		return false;
	}
	
	public static boolean performEmailUpdate(String userID) {
		//TODO make the tempEmail the primary email.
		//TODO return success or not
		return false;
	}
	
	public static boolean confirmationKeyMatches(String confirmationKey) {
		//TODO check that the confirmationKey in the DB matches the supplies
		//return success or not
		return false;
	}
	
	public static boolean verifyPassword(String userID, String password) {
		//TODO check that the password passed in matches the password in the DB (this is POST encryption)
		//TODO return success
		return false;
	}
	
	public static boolean updatePassword(String userID, String newPassword) {
		//TODO update the DB with the newPassword (POST ENCRYPTION)
		//TODO return success
		return false;
	}
	
	/**
	 * Creates a random key
	 * @return
	 */
	public static String createRandomKey() {
		String charsAllowed = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
				+ "1234567890!@#$%^&*()");
		int length = 16;
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		
		for (int i = 0; i < length; i++){
			int index = rnd.nextInt(charsAllowed.length());
			sb.append(charsAllowed.charAt(index));
		}
		
		return sb.toString();
		
		
		
	}
}
