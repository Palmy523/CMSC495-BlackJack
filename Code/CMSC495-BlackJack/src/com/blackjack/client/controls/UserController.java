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
	
	/**
	 * Navigates to the Room Selection Screen on login success
	 * 
	 * @param event
	 */
	public void onLoginSuccess(LoginEvent event) {
		dashboard.displayRoomSelectionScreen();
		user = event.getUser();
	}
	
	/**
	 * Display error on login failure
	 * 
	 * @param event
	 */
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
	 * Method to perform account creation is successful.
	 * 
	 * @param event the loginEvent that prompted the login
	 */
	public void onCreateAccountSuccess(CreateAccountEvent event) {
		dashboard.displayLoginScreen();
		dashboard.displayMessage(MessageType.INFO, "Your account has been successfully created, "
				+ "please Login to play.");
	}
	
	/**
	 * Method to perform when account creation is a failure.
	 * 
	 * @param event
	 */
	public void onCreateAccountFailure(CreateAccountEvent event) {
		String errorMessage = "An error occurred";
		if (event.isUserNameTaken()) {
			errorMessage = "Sorry, that user name is already taken.";
		} else if (event.isEmailTaken()) {
			errorMessage = "Sorry, that email is already in use.";
		}
		dashboard.displayMessage(MessageType.INFO, errorMessage);
	}
	
	/**
	 * Resets a password for the user with the supplied email address.
	 * @param emailAddress the email adress of the user to reset the pasword for.
	 */
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
	}
	
	/**
	 * Method to perform when reset password is success
	 * 
	 * @param event
	 */
	public void onResetPasswordSuccess(ResetPasswordEvent event) {
		dashboard.displayMessage(MessageType.INFO, "An email has been sent to the supplied email address with " 
					+ "a temporary password.");
	}
	
	/**
	 * Method to perform when a password reset fails
	 * @param event
	 */
	public void onResetPasswordFailure(ResetPasswordEvent event) {
		if (event.isEmailInvalid()) {
			dashboard.displayMessage(MessageType.INFO, "The email address you supplied is not in our system, "
					+ "please provide a valid email address");
		}
	}
	
	/**
	 * Calls the server to update the email address for the userID and 
	 * @param userID
	 * @param newEmailAddress
	 */
	public void updatEmail(String newEmailAddress) {
		checkLogin();
		if (!isUserLoggedIn()) {
			return;
		}
			
		if (FieldVerifier.isValidEmail(newEmailAddress) == FormatError.INVALID_FORMAT) {
			dashboard.displayMessage(MessageType.ERROR, FieldVerifier.EMAIL_ADDRESS_ERROR);
			return;
		}
		
		AsyncCallback<UpdateEmailEvent> callback = new AsyncCallback<UpdateEmailEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				dashboard.displayMessage(MessageType.ERROR, COMM_FAILURE_MESSAGE);
			}

			@Override
			public void onSuccess(UpdateEmailEvent result) {
				if (result.isSuccess()) {
					onUpdateEmailSuccess(result);
				} else {
					onUpdateEmailFailure(result);
				}
			}

        };

		String userID = String.valueOf(user.getUserID());
		service.updateEmail(userID, newEmailAddress, callback);
	}
	
	/**
	 * Method to perform when email update is a success, display the confirmaiton box
	 * 
	 * @param event
	 */
	public void onUpdateEmailSuccess(UpdateEmailEvent event) {
		dashboard.displayEmailConfirmationPanel(true);
	}
	
	/**
	 * Method to perform when email update fails, display error
	 * @param event
	 */
	public void onUpdateEmailFailure(UpdateEmailEvent event) {
		dashboard.displayMessage(MessageType.INFO, "Email update failed!");
	}
	
	
	/**
	 * Confirms the email update process using the supplied confirmation key
	 * 
	 * @param confirmationKey
	 */
	public void confirmEmail(String confirmationKey) {
		checkLogin();
		if (!isUserLoggedIn()) {
			return;
		}
		
		FormatError error = FieldVerifier.isValidPassword(confirmationKey);
		if (error == FormatError.INVALID_CHARACTER || error == FormatError.LENGTH) {
			dashboard.displayMessage(MessageType.ERROR, "Sorry, the confimation key you entered is not valid");
			return;
		}
	
		String userId = String.valueOf(user.getUserID());
		AsyncCallback<ConfirmEmailEvent> callback = new AsyncCallback<ConfirmEmailEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				dashboard.displayMessage(MessageType.ERROR, COMM_FAILURE_MESSAGE);
			}

			@Override
			public void onSuccess(ConfirmEmailEvent result) {
				if (result.isSuccess()) {
					onConfirmEmailSuccess(result);
				} else {
					onConfirmEmailFailure(result);
				}
			}
		};
		
	}
	
	/**
	 * Method to perform when confirmation of email is a success, close the confirmation panel
	 * @param result
	 */
	public void onConfirmEmailSuccess(ConfirmEmailEvent result) {
		dashboard.displayMessage(MessageType.INFO, "Your email has been updated successfully");
		dashboard.displayEmailConfirmationPanel(false);
	}
	
	/**
	 * Method to perform when confirmation of email fails, display error message
	 * @param event
	 */
	public void onConfirmEmailFailure(ConfirmEmailEvent event) {
		dashboard.displayMessage(MessageType.INFO, "Email update failed!");
	}
	
	/**
	 * Updates the password for the logged in user.
	 * 
	 * @param currentPassword the current password of the user
	 * @param newPassword the new password to update the password with
	 * @param confirmNewPassword the confirmed password that matches the new password
	 */
	public void updatePassword(String currentPassword, String newPassword, String confirmNewPassword) {
		checkLogin();
		if (!isUserLoggedIn()) {
			return;
		}
		
		if (!(newPassword.equals(confirmNewPassword))) {
			dashboard.displayMessage(MessageType.ERROR, "The new passwords do not match");
			return;
		}
		
		if(FieldVerifier.isValidPassword(currentPassword) == FormatError.LENGTH 
				|| FieldVerifier.isValidPassword(currentPassword) == FormatError.INVALID_CHARACTER) {
			dashboard.displayMessage(MessageType.ERROR, "The current password is invalid");
			return;
		}
		
		if(FieldVerifier.isValidPassword(currentPassword) == FormatError.LENGTH) {
			dashboard.displayMessage(MessageType.ERROR, FieldVerifier.PASSWORD_LENGTH_ERROR);
			return;
		}
		
		if (FieldVerifier.isValidPassword(currentPassword) == FormatError.INVALID_CHARACTER) {
			dashboard.displayMessage(MessageType.ERROR, FieldVerifier.PASSWORD_REGEX_ERROR);
		}
		
		String userID = String.valueOf(user.getUserID());
		AsyncCallback callback = new AsyncCallback<UpdatePasswordEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				dashboard.displayMessage(MessageType.ERROR, COMM_FAILURE_MESSAGE);
			}

			@Override
			public void onSuccess(UpdatePasswordEvent result) {
				if (result.isSuccess()) {
					onUpdatePasswordSuccess(result);
				} else {
					onUpdatePasswordFailure(result);
				}
			}
		};
		
		service.updatePassword(userID, currentPassword, confirmNewPassword, callback);
	}
	
	/**
	 * Method to perform when updating a password is a success, display success message
	 * @param event
	 */
	public void onUpdatePasswordSuccess(UpdatePasswordEvent event) {
		dashboard.displayMessage(MessageType.INFO, "Your password has been successfully updated!");
	}

	/**
	 * Method to perform when updating a password has failed, display an error message.
	 * @param event
	 */
	public void onUpdatePasswordFailure(UpdatePasswordEvent event) {
		//TODO display appropriate message
		if (event.isWrongCurrentPassword()) {
			dashboard.displayMessage(MessageType.ERROR, "The current password supplied is incorrect");
		} else {
			dashboard.displayMessage(MessageType.INFO, "Something went wrong when updating your password, "
        		+ "please try again later");       
		}
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