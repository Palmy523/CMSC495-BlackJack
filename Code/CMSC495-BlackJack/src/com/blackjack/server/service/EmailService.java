package com.blackjack.server.service;

public class EmailService {

	public EmailService() {
		
	}
	
	
	
	
	/**
	 * Sends an email to the supplied email address with the 
	 * temporary confirmation key to allow the email update.
	 * 
	 * @param email the email address to send the key to.
	 * @return true if the message was sent successfully, false if otherwise
	 */
	public static boolean sendEmailUpdateKey(String email) {
		//TODO send an email with the Email Update key to update email
	
		//TODO return success
		return false;
	}
	
	/**
	 * Sends an email to the supplied email address with the
	 * temporary password created for their account. 
	 * 
	 * @param email the email address to send the temporary password to.
	 * @return true if the message was sent successfully, false if otherwise.
	 */
	public static boolean sendTemporaryPassword(String email) {
		//TODO send an email with a temporary password
		
		//TODO return success
		return false;
	}
	
}
