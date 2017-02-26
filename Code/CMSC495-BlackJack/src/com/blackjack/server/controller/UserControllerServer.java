package com.blackjack.server.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

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
		System.out.println(password);
		System.out.println(password.length());
		password = MD5EncryptionService.encrypt(password);
		System.out.println(password);
		System.out.println(password.length());
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
			System.out.println(id);
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
			user.setLastLogin(rs.getDate(7));
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
	
	/**
	 * Creates a new account and initializes their game data
	 * @param username the username of the user account to create
	 * @param password the password of the user account to create
	 * @param email the email of the user account to create
	 * @return the newly created User object that represents the user
	 */
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

	
	/**
	 * Creates a random password and updates the database with the new password
	 * @param emailAddress the email address of the user to perform the update on
	 * @return the new password if the update was successful, else null
	 */
	public static boolean resetPassword(String emailAddress, String newPassword) {
		String newPasswordEncrypted = MD5EncryptionService.encrypt(newPassword);
		System.out.println(newPassword);
		System.out.println(newPassword.length());
		System.out.println(newPasswordEncrypted);
		System.out.println(newPasswordEncrypted.length());
		if (!emailExists(emailAddress)) {
			return false;
		}
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE user "
					+ "SET password = ? "
					+ "WHERE email = ?;");
			ps.setString(1, newPasswordEncrypted);
			ps.setString(2, emailAddress);
			if (ps.executeUpdate() > 0) {
				return true;
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
		
		return false;
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
	 * Creates and returns a temporary email update key and stores it in the 
	 * database to await for a user to confirm the email update.
	 * @param userID the userID to create the key for
	 * @return the newly created key unencrypted
	 */
	public static boolean createTemporaryEmailUpdateKey(String userID, String tempKey) {
		String tempKeyEncrypted = MD5EncryptionService.encrypt(tempKey);
		int userIDInt = Integer.valueOf(userID);
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("UPDATE user "
					+ "SET email_key = ? "
					+ "WHERE user_id = ? ;");
			ps.setString(1, tempKeyEncrypted);
			ps.setInt(2, userIDInt);
			int value = ps.executeUpdate();
			if (value > 0) {
				return true;
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
		return false;
	}
	
	/**
	 * Sets a temporary email for a user while waiting to confirm their email
	 * update with a confirmation key.
	 * @param userID the userID of the User to create the temporary email for
	 * @param email the email to set as the temp
	 * @return true if the operation was successful, false if otherwise
	 */
	public static boolean createTemporaryEmail(String userID, String email) {
		int userIDInt = Integer.valueOf(userID);
		boolean success = false;
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("UPDATE user "
					+ "SET temp_email = ? "
					+ "WHERE user_id = ? ;");
			ps.setString(1, email);
			ps.setInt(2, userIDInt);
			
			int value = ps.executeUpdate();
			if (value > 0) {
				success = true;
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
		return success;
	}
	
	public static boolean performEmailUpdate(String userID) {
		int userIDInt = Integer.valueOf(userID);
		boolean success = false;
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT temp_email "
					+ "FROM user "
					+ "WHERE user_id = ? ;");
			ps.setInt(1, userIDInt);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.first();
				String email = rs.getString(1);
				ps.close();
				
				ps = conn.prepareStatement("UPDATE user "
						+ "SET email = ? "
						+ "WHERE user_id = ? ;");
				ps.setString(1, email);
				ps.setInt(2, userIDInt);
				int value = ps.executeUpdate();
				if (value > 0) {
					success = true;
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
		
		return success;
	}
	
	public static boolean confirmationKeyMatches(String confirmationKey) {
		confirmationKey = MD5EncryptionService.encrypt(confirmationKey);
		boolean isValid = false;
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT Count(user_id) "
					+ "FROM user "
					+ "WHERE email_key = ? ;");
			ps.setString(1, confirmationKey);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.first();
				if (rs.getInt(1) > 0) {
					isValid = true;
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
		return isValid;
	}
	
	/**
	 * Verifies that the password is correct for a user.
	 * @param userID the user to check the password for
	 * @param password the password to check
	 * @return true if the password supplied matches the password stored in the DB,
	 * false if otherwise
	 */
	public static boolean verifyPassword(String userID, String password) {
		
		int userIDInt = Integer.valueOf(userID);
		password = MD5EncryptionService.encrypt(password);
		boolean isValid = false;
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT Count(user_id) FROM user WHERE user_id = ? && password = ? ");
			ps.setInt(1, userIDInt);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.first();
				if (rs.getInt(1) > 0) {
					isValid = true;
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
		return isValid;
	}
	
	/**
	 * Updates the current password in the DB with the new Password
	 * @param userID the userID of the user to set the new password for
	 * @param newPassword the password to set
	 * @return true if the operation was a success, false if otherwise
	 */
	public static boolean updatePassword(String userID, String newPassword) {
		
		int userIDInt = Integer.valueOf(userID);
		newPassword = MD5EncryptionService.encrypt(newPassword);
		boolean success = false;
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE user "
					+ "SET password = ? "
					+ "WHERE user_id = ? ;");
			ps.setString(1, newPassword);
			ps.setInt(2, userIDInt);
			if (ps.executeUpdate() > 0) {
				success = true;
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
		
		return success;
	}
	
	public static float updateChipCount(String userID, float amount) {
		//TODO update the database with the new amount using controller

		int userIDInt = Integer.valueOf(userID);
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("Select bank_amt "
					+ "FROM game_data "
					+ "WHERE user_id = ? ;");
			ps.setInt(1, userIDInt);
			rs = ps.executeQuery();
			if (rs.first()) {
				amount = rs.getFloat(1) + amount;
				if (amount < 0) {
					amount = 0;
				}
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement("UPDATE game_data "
						+ "SET bank_amt = ? "
						+ "WHERE user_id = ? ;");
				ps.setFloat(1, amount);
				ps.setInt(2, userIDInt);
				if (ps.executeUpdate() > 0) {
					return amount;
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
		//TODO return the amount if update was successful, else return the old amount
		// or return -1 if any db actions were unsuccessful
		return -1;
	}
	
	public static Date updateLastLogin(String username) {

		int userIDInt = Integer.valueOf(username);
		Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		Connection conn = ConnectionService.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {							
				ps = conn.prepareStatement("UPDATE user "
						+ "SET last_login = ? "
						+ "WHERE user_name = ? ;");
				ps.setDate(1, currentDate);
				ps.setString(2, username);
				if (ps.executeUpdate() > 0) {
					return currentDate;
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
		//fail
		return null;
	}
}
