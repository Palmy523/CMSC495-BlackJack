package com.blackjack.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.regexp.shared.RegExp;

/**
 * Javascript client side implementation of input verification.
 */
public class FieldVerifier {

	public static String USER_REGEX_ERROR = "Sorry, the username can only contain characters"
			+ " A-Z, a-z,0-9, and '_'. Please enter a valid username.";
	public static String USER_LENGTH_ERROR = "Sorry, the username can only be between 3 "
			+ " and 16 characters in length. Please enter a valid username.";
	public static String PASSWORD_REGEX_ERROR = "Sorry, the password contains illegal characters. "
			+ " Passwords can only contain A-Z, a-z, 0-9, and special characters !@#$%^&*()";
	public static String PASSWORD_LENGTH_ERROR = "Sorry, the password does not meet the length "
			+ " requirements. Passwords can only be between 8-16 characters in length.";
	public static String EMAIL_ADDRESS_ERROR = "Sorry, the email address is invalid.";
	
	/**
	 * Error codes for what caused the regex to fail.
	 */
	public enum FormatError {WHITE_SPACE, LENGTH, INVALID_CHARACTER, NONE, INVALID_FORMAT }
	
	/**
	 * Regexp for checking usernames 
	 */
	public static RegExp usernameRegex = RegExp.compile("^[a-zA-Z0-9_]+$");
	
	/**
	 * Regexp for checking password inputs.
	 */
	public static RegExp passwordRegex = RegExp.compile("^[a-zA-Z0-9!@#$%^&*()]+$");
	
	/**
	 * Regex for checking email inputs.
	 */
	public static RegExp emailRegex = RegExp.compile("\\S+@\\S+\\.\\S+");
	
	/**
	 * Checks if the supplied username is valid.
	 * @param name the username to check
	 * @return FormatError.NONE if all requirements met, or a FormatError that determines
	 * what caused the regex to fail.
	 */
	public static FormatError isValidUsername(String name) {
		//TODO make sure these work
		GWT.log("/^[a-zA-Z0-9_]+$/");
		GWT.log(usernameRegex.getSource());
		GWT.log("/^[a-zA-Z0-9!@#$%^&*()]+$/");
		GWT.log(passwordRegex.getSource());
		GWT.log("/\\S+@\\S+\\.\\S+/");
		GWT.log(emailRegex.getSource());

		if (name.length() < 3 || name.length() > 16) {
			return FormatError.LENGTH;
		}
		if (!usernameRegex.test(name)) {
			return FormatError.INVALID_CHARACTER;
		}
		return FormatError.NONE;
	}
	
	/**
	 * Validates a password for proper format
	 * @param password the password to check.
	 * @return FormatError.NONE if all requirements met, or a FormatError that determines
	 * what caused the regex to fail.	 
	 */
	public static FormatError isValidPassword(String password) {
		//TODO make sure these work
		if (password.length() < 8 || password.length() > 16) {
			return FormatError.LENGTH;
		}
		if (!passwordRegex.test(password)) {
			return FormatError.INVALID_CHARACTER;
		}
		return FormatError.NONE;
	}
	
	/**
	 * Validates an email input 
	 * @param email the email to validate.
	 * @return FormatError.NONE if all requirements met, or a FormatError that determines
	 * what caused the regex to fail.
	 */
	public static FormatError isValidEmail(String email) {
		//TODO make sure these work
		if (!emailRegex.test(email)) {
			return FormatError.INVALID_FORMAT;
		}
		return FormatError.NONE;
	}
}
