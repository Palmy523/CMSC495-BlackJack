package com.blackjack.client.controls;

import com.blackjack.client.event.Events;
import com.blackjack.client.service.UserService;
import com.blackjack.client.service.UserServiceAsync;
import com.blackjack.client.ui.Dashboard;
import com.blackjack.client.ui.UserMessageBox.MessageType;
import com.blackjack.shared.FieldVerifier;
import com.blackjack.shared.FieldVerifier.FormatError;
import com.blackjack.shared.entities.User;
import com.blackjack.shared.events.ConfirmEmailEvent;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UserController {

	public static final String COMM_FAILURE_MESSAGE = "An unkown error has occurred, please try again later";
	
	private Dashboard dashboard;
	private User user;
	private UserServiceAsync service = GWT.create(UserService.class);
	
	public UserController(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	/**
	 * Performs a login.
	 * 
	 * @param username the username of a currently existing user
	 * @param password the password for the user
	 */
	public void login(String username, String password) {
		
		//TODO verify username and password formats
		
		AsyncCallback<LoginEvent> callback = new AsyncCallback<LoginEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				dashboard.displayMessage(MessageType.ERROR, COMM_FAILURE_MESSAGE);
			}

			@Override
			public void onSuccess(LoginEvent result) {
				Events.eventBus.fireEvent(result);
				if (result.isSuccess()) {
					onLoginSuccess(result);
				} else {
					onLoginFailure(result);
				}
			}
		};
		
		service.login(username, password, callback);
	}
	
	public void onLoginSuccess(LoginEvent event) {
		dashboard.displayRoomSelectionScreen();
		user = event.getUser();
	}
	
	public void onLoginFailure(LoginEvent event) {
		String message = "";
		if (event.isUsernameInvalid()) {
			message = "The supplied username is invalid";
		}
		if (event.isPasswordInvalid()) {
			message = "The password is invalid.";
		}
		dashboard.displayMessage(MessageType.ERROR, message);
	}
	
	/**
	 * Creates an account with the specified information.
	 * @param username the username to create the account with
	 * @param password the password to create the account with
	 * @param confirmPassword the confirmation of the password to ensure the user
	 * typed in exactly what they meant
	 * @param email the email address to create the account with.
	 */
	public void createAccount(String username, 
			String password, 
			String confirmPassword, 
			String email) {
		
		if (FieldVerifier.isValidEmail(email) == FormatError.INVALID_FORMAT) {
			//dashboard.displayMessage(MessageType.INFO, FieldVerifier.EMAIL_ADDRESS_ERROR);
		}
		
		if (FieldVerifier.isValidPassword(password) == FormatError.LENGTH) {
			//dashboard.displayMessage(MessageType.INFO, FieldVerifier.PASSWORD_LENGTH_ERROR);
		}
		
		if (FieldVerifier.isValidPassword(password) == FormatError.INVALID_FORMAT) {
			//dashboard.displayMessage(MessageType.INFO, FieldVerifier.PASSWORD_REGEX_ERROR);
		}
		
		if (FieldVerifier.isValidEmail(email) == FormatError.INVALID_FORMAT) {
			//dashboard.displayMessage(MessageType.INFO, FieldVerifier.EMAIL_ADDRESS_ERROR);
		}
		
		if (!password.equals(confirmPassword)) {
			dashboard.displayMessage(MessageType.INFO, "Sorry, the passwords you provided do not match.");
			return;
		}
		
		AsyncCallback<CreateAccountEvent> callback = new AsyncCallback<CreateAccountEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				dashboard.displayMessage(MessageType.ERROR, COMM_FAILURE_MESSAGE);
			}

			@Override
			public void onSuccess(CreateAccountEvent result) {
				Events.eventBus.fireEvent(result);
				if (result.isSuccess()) {
					onCreateAccountSuccess(result);
				} else {
					onCreateAccountFailure(result);
				}
			}
		};
		
		service.createAccount(username, confirmPassword, email, callback);
	}
	
	/**
	 * Method to perform when login is a success.
	 * 
	 * @param event the loginEvent that prompted the login
	 */
	public void onCreateAccountSuccess(CreateAccountEvent event) {
		dashboard.displayLoginScreen();
		dashboard.displayMessage(MessageType.INFO, "Your account has been successfully created, "
				+ "please Login to play.");
	}
	
	public void onCreateAccountFailure(CreateAccountEvent event) {
		String errorMessage = "An error occurred";
		if (event.isUserNameTaken()) {
			errorMessage = "Sorry, that user name is already taken.";
		} else if (event.isEmailTaken()) {
			errorMessage = "Sorry, that email is already in use.";
		}
		dashboard.displayMessage(MessageType.INFO, errorMessage);
	}
	
	public void resetPassword(String emailAddress) {
		
		if (FieldVerifier.isValidEmail(emailAddress) == FormatError.INVALID_FORMAT) {
			dashboard.displayMessage(MessageType.ERROR, FieldVerifier.EMAIL_ADDRESS_ERROR);
			return;
		}
		
		AsyncCallback<ResetPasswordEvent> callback = new AsyncCallback<ResetPasswordEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				dashboard.displayMessage(MessageType.ERROR, COMM_FAILURE_MESSAGE);
			}

			@Override
			public void onSuccess(ResetPasswordEvent result) {
				if (result.isSuccess()) {
					onResetPasswordSuccess(result);
				} else {
					onResetPasswordFailure(result);
				}
				
			}
			
		};
		service.resetPassword(emailAddress, callback);
		
		//Call the UserService to reset the password
		
	}
	
	public void onResetPasswordSuccess(ResetPasswordEvent event) {
		dashboard.displayMessage(MessageType.INFO, "An email has been sent to the supplied email address with " 
					+ "a temporary password.");
	}
	
	public void onResetPasswordFailure(ResetPasswordEvent event) {
		if (event.isEmailInvalid()) {
			dashboard.displayMessage(MessageType.INFO, "The email address you supplied is not in our system, "
					+ "please provide a valid email address");
		}
	}
	
	public void updatEmail(String userID, String newEmailAddress) {
		//TODO verify new email format
		
		//TODO call the UserService to perform the update
		
	}
	
	public void onUpdateEmailSuccess() {
		//TODO display the confirmation text box for entry of the 
		//confirmation key sent to email
	}
	
	public void onUpdateEmailFailure(UpdateEmailEvent event) {
		//TODO display error
	}
	
	
	public void confirmEmail(String userID, String confirmationKey) {
		//TODO very confirmation key format
		
		//TODO call the UserService
	}
	
	public void onConfirmEmailSuccess() {
		//TODO display success message, remove confirmation screen
	}
	
	public void onConfirmEmailFailure(ConfirmEmailEvent event) {
		//TODO display an error message
	}
	
	public void updatePassword(String currentPassword, String oldPassword) {
		checkLogin();
		//TODO verify password formats are correct
		//TODO send action to userservice
	}
	
	public void onUpdatePasswordSuccess(UpdatePasswordEvent event) {
		//TODO display success message
	}
	
	public void onUpdatePasswordFailure(UpdatePasswordEvent event) {
		//TODO display error message
	}
	
	/**
	 * @return true if the user has logged in and the user object is available
	 * false if otherwise
	 */
	public boolean isUserLoggedIn() {
		return (user != null);
	}
	
	/**
	 * Checks the login status of the current user. If the user is not logged in
	 * return them to the Login Screen and display an info message to log back in.
	 */
	public void checkLogin() {
		if (!isUserLoggedIn()) {
			dashboard.displayLoginScreen();
			dashboard.displayMessage(MessageType.INFO, 
					"You have been logged out, please log back in.");
		}
	}
	
}
