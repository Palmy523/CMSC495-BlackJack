package com.blackjack.server.service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class EmailService {

	private static final String EMAIL = "21.blackjack.cmsc495@gmail.com";
	private static final String PASSWORD = "H3k&^3jdsse";

	    private static boolean sendEmail(String email, String subject, String messageText) {
	      boolean success = false;
			Properties properties;
            
			// Get system properties
            properties = System.getProperties();
            // Setup mail server
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            
	        // Get the default Session object.
	        Session session = Session.getInstance(properties,
	            new javax.mail.Authenticator() {
	          protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(EMAIL, PASSWORD);
	          }
	          });

	      try {
	        // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(EMAIL));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

	            // Set Subject: header field
	            message.setSubject(subject);

	           // Now set the actual message
	          message.setText(messageText);

	          // Send message
	          Transport.send(message);
	          success = true;
	      } catch(Exception ex) {
	        System.out.println(ex.toString());
	        success = false;
	      }
	        return success;
	    }
	    
	/**
	 * Sends an email to the supplied email address with the 
	 * temporary confirmation key to allow the email update.
	 * 
	 * @param email the email address to send the key to.
	 * @return true if the message was sent successfully, false if otherwise
	 */
	public static boolean sendEmailUpdateKey(String email, String confirmationKey) {
		String message = "You are receiving this email because you requested to update your "
				+ "account email address. Your confirmation key is " + confirmationKey + "./n/r/n/r"
				+ "To complete the update please enter the code in the text box provided on our website."
				+ " If you did not request an email update, someone is probably trying to sabotage you.";
		return sendEmail(email, "21! Email Update Confirmation", message);
	}
	
	/**
	 * Sends an email to the supplied email address with the
	 * temporary password created for their account. 
	 * 
	 * @param email the email address to send the temporary password to.
	 * @return true if the message was sent successfully, false if otherwise.
	 */
	public static boolean sendTemporaryPassword(String email, String tempPassword) {
		String message = "You are receiving this email because you requested a password reset. "
				+ "You may use the temporary password " + tempPassword + " to log into your account. "
				+ "Please update your password when you have logged in to something safe. If "
				+ "you did not request a password reset, someone is probably trying to sabotage you.";
		return sendEmail(email, "21! Password Reset", message);
	}
	
}
