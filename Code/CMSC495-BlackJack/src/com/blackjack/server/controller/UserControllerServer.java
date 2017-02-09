package com.blackjack.server.controller;

import com.blackjack.shared.entities.User;

public class UserControllerServer {

	/**
	 * Checks the database for proper credentials and returns a LoginEvent
	 * with proper fields based on the success or failure of the login.
	 * 
	 * @param username 
	 * @param password
	 * @return
	 */
	public User login(String username, String password) {
		//TODO encrypt password using MD5EncryptionService, check encrypted value
		//with database value and username
		
		//TODO return the User object if successful, or null if not
		
		return null;
	}
	
	public User createAccount(String username, String password, String email) {
		//TODO check current database usernames and emails to ensure no duplicates
		
		//TODO encrypt the password value using MD5EncryptionService
		
		//TODO create the account and User object
		
		//TODO return the created User if successful, null if not
		
		return null;
	}
	
	public int updateChipCount(String userID, int amount) {
		//TODO update the database with the new amount using controller
		
		//TODO return the amount if update was successful, else return the old amount
		// or return -1 if any db actions were unsuccessful
		return -1;
	}
	
	public boolean resetPassword(String emailAddress) {
		//TODO create a temporary password for the account with the email address
		
		//TODO return success
		return false;
	}
	
	public boolean userExists(String userID) {
		//TODO check if the user exists in the database
		//TODO return success
		return false;
	}
	
	public boolean userNameExists(String username) {
		//TODO check if the username exists in the database
		//TODO return true if userNameExists false if not
		return false;
	}
	
	public boolean emailExists(String emailAddress) {
		//TODO check if the email exists in the database
		
		//TODO return if it is or not.
		return false;
	}	
	
	/**
	 * Creates a temporary email update key in the DB
	 * @param userID the userID of the account to create the key for 
	 * to update
	 * @return the confirmationKey that was created to confim the update
	 */
	public String createTemporaryEmailUpdateKey(String userID) {
		//TODO create a key
		//TODO store in database
		//TODO return the created key
		return null;
	}
	
	public boolean createTemporaryEmail(String userID, String email) {
		//TODO set the tempEmail for the userID in the DB
		//TODO return success or not
		return false;
	}
	
	public boolean performEmailUpdate(String userID) {
		//TODO make the tempEmail the primary email.
		//TODO return success or not
		return false;
	}
	
	public boolean confirmationKeyMatches(String confirmationKey) {
		//TODO check that the confirmationKey in the DB matches the supplies
		//return success or not
		return false;
	}
	
	public boolean verifyPassword(String userID, String password) {
		//TODO check that the password passed in matches the password in the DB (this is POST encryption)
		//TODO return success
		return false;
	}
	
	public boolean updatePassword(String userID, String newPassword) {
		//TODO update the DB with the newPassword (POST ENCRYPTION)
		//TODO return success
		return false;
	}
}
