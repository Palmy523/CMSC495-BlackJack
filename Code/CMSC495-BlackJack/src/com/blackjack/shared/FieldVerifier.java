package com.blackjack.shared;

import com.google.gwt.regexp.shared.RegExp;

/**
 * <p>
 * FieldVerifier validates that the name the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> package because we use it in both
 * the client code and on the server. On the client, we verify that the name is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the name is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is not translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client-side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
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
	
	public enum FormatError {WHITE_SPACE, LENGTH, INVALID_CHARACTER, NONE, INVALID_FORMAT }
	public static RegExp usernameRegex = RegExp.compile("/^[A-Za-z0-9_]$");
	public static RegExp passwordRegex = RegExp.compile("/^[A-Za-z0-9!@#$%^&*()]$");
	public static RegExp emailRegex = RegExp.compile("/\\S+@\\S+\\.\\S+/");
	
	public static FormatError isValidUsername(String name) {
		if (name.length() < 3 || name.length() > 16) {
			return FormatError.LENGTH;
		}
		if (usernameRegex.test(name)) {
			return FormatError.INVALID_CHARACTER;
		}
		return FormatError.NONE;
	}
	
	public static FormatError isValidPassword(String password) {
		if (password.length() < 8 || password.length() > 16) {
			return FormatError.LENGTH;
		}
		if (!passwordRegex.test(password)) {
			return FormatError.INVALID_CHARACTER;
		}
		return FormatError.NONE;
	}
	
	public static FormatError isValidEmail(String email) {
		if (!emailRegex.test(email)) {
			return FormatError.INVALID_FORMAT;
		}
		return FormatError.NONE;
	}
}
