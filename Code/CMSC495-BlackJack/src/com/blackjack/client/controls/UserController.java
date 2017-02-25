package com.blackjack.client.controls;

import com.blackjack.client.Config;
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
import com.blackjack.shared.events.UpdateChipEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UserController {

public static final String COMM_FAILURE_MESSAGE = "An unkown error has occurred, please try again later";
	
	private static Dashboard dashboard;
	private static User user;
	private static UserServiceAsync service = GWT.create(UserService.class);
	
	static {
		//TODO remove testing info
		if (Config.IS_TESTING) {
			user = new User();
			user.setBankAmount(10000.50f);
			user.setEasyPlay(false);
			user.setEmail("iTestThings@email.com");
			user.setMaxChips(25000f);
			user.setUserID(1);
			user.setUsername("TestsTheBest");
		}
	}
	
	/**
	 * Performs a login.
	 * 
	 * @param username the username of a currently existing user
	 * @param password the password for the user
	 */
	public static void login(String username, String password) {
		
		if (Config.IS_TESTING) {
			dashboard.displayRoomSelectionScreen();
			UpdateChipEvent event = new UpdateChipEvent();
			event.setNewAmount(10455.50f);
			event.setSuccess(true);
			user.setBankAmount(10455.50f);
			Events.eventBus.fireEvent(event);
			LoginEvent loginEvent = new LoginEvent();
			loginEvent.setUser(user);
			loginEvent.setSuccess(true);
			Events.eventBus.fireEvent(loginEvent);
			return;
		}
		
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
	public static void onLoginSuccess(LoginEvent event) {
		dashboard.displayRoomSelectionScreen();
		user = event.getUser();
	}
	
	/**
	 * Display error on login failure
	 * 
	 * @param event
	 */
	public static void onLoginFailure(LoginEvent event) {
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
	public static void createAccount(String username, 
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
	public static void onCreateAccountSuccess(CreateAccountEvent event) {
		dashboard.displayLoginScreen();
		dashboard.displayMessage(MessageType.INFO, "Your account has been successfully created, "
				+ "please Login to play.");
	}
	
	/**
	 * Method to perform when account creation is a failure.
	 * 
	 * @param event
	 */
	public static void onCreateAccountFailure(CreateAccountEvent event) {
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
	public static void resetPassword(String emailAddress) {
		
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
				Events.eventBus.fireEvent(result);
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
	public static void onResetPasswordSuccess(ResetPasswordEvent event) {
		dashboard.displayLoginScreen();
		dashboard.displayMessage(MessageType.INFO, "An email has been sent to the supplied email address with " 
				+ "a temporary password.");
	}
	
	/**
	 * Method to perform when a password reset fails
	 * @param event
	 */
	public static void onResetPasswordFailure(ResetPasswordEvent event) {
		if (event.isEmailInvalid()) {
			dashboard.displayMessage(MessageType.INFO, "The email address you supplied is not in our system, "
					+ "please provide a valid email address");
		} else {
			dashboard.displayMessage(MessageType.INFO, "Something went wrong "
					+ "resetting your password, please try again later");
		}
	}
	
	/**
	 * Calls the server to update the email address for the userID and 
	 * @param userID
	 * @param newEmailAddress
	 */
	public static void updatEmail(String newEmailAddress) {
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
				Events.eventBus.fireEvent(result);
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
	public static void onUpdateEmailSuccess(UpdateEmailEvent event) {
		dashboard.displayConfirmEmailForm();
	}
	
	/**
	 * Method to perform when email update fails, display error
	 * @param event
	 */
	public static void onUpdateEmailFailure(UpdateEmailEvent event) {
		if (event.isEmailInvalid()) {
			dashboard.displayMessage(MessageType.ERROR, "Sorry, the email you gave is already in use, "
					+ "please choose another email address.");
		} else {
			dashboard.displayMessage(MessageType.INFO, "Email update failed!");
		}
	}
	
	
	/**
	 * Confirms the email update process using the supplied confirmation key
	 * 
	 * @param confirmationKey
	 */
	public static void confirmEmail(String confirmationKey) {
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
				Events.eventBus.fireEvent(result);
				if (result.isSuccess()) {
					onConfirmEmailSuccess(result);
				} else {
					onConfirmEmailFailure(result);
				}
			}
		};
		
		service.confirmEmail(userId, confirmationKey, callback);
	}
	
	/**
	 * Method to perform when confirmation of email is a success, close the confirmation panel
	 * @param result
	 */
	public static void onConfirmEmailSuccess(ConfirmEmailEvent result) {
		dashboard.displayMessage(MessageType.INFO, "Your email has been updated successfully");
		dashboard.displayEmailConfirmationPanel(false);
	}
	
	/**
	 * Method to perform when confirmation of email fails, display error message
	 * @param event
	 */
	public static void onConfirmEmailFailure(ConfirmEmailEvent event) {
		dashboard.displayMessage(MessageType.INFO, "Email update failed!");
	}
	
	/**
	 * Updates the password for the logged in user.
	 * 
	 * @param currentPassword the current password of the user
	 * @param newPassword the new password to update the password with
	 * @param confirmNewPassword the confirmed password that matches the new password
	 */
	public static void updatePassword(String currentPassword, String newPassword, String confirmNewPassword) {
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
		AsyncCallback<UpdatePasswordEvent> callback = new AsyncCallback<UpdatePasswordEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				dashboard.displayMessage(MessageType.ERROR, COMM_FAILURE_MESSAGE);
			}

			@Override
			public void onSuccess(UpdatePasswordEvent result) {
				Events.eventBus.fireEvent(result);
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
	public static void onUpdatePasswordSuccess(UpdatePasswordEvent event) {
		dashboard.displayMessage(MessageType.INFO, "Your password has been successfully updated!");
	}

	/**
	 * Method to perform when updating a password has failed, display an error message.
	 * @param event
	 */
	public static void onUpdatePasswordFailure(UpdatePasswordEvent event) {
		//TODO display appropriate message
		if (event.isWrongCurrentPassword()) {
			dashboard.displayMessage(MessageType.ERROR, "The current password supplied is incorrect");
		} else {
			dashboard.displayMessage(MessageType.INFO, "Something went wrong when updating your password, "
        		+ "please try again later");       
		}
	}
	
	/**
	 * Updates the user account with the new amount.
	 * 
	 * @param amount
	 */
	public static void updateChipCount(float amount) {
		if (Config.IS_TESTING) {
			user.setBankAmount(user.getBankAmount() + amount);
			UpdateChipEvent event = new UpdateChipEvent();
			event.setNewAmount(user.getBankAmount());
			event.setSuccess(true);
			Events.eventBus.fireEvent(event);
		} else {
			AsyncCallback<UpdateChipEvent> callback = new AsyncCallback<UpdateChipEvent>() {

				@Override
				public void onFailure(Throwable caught) {
					dashboard.displayMessage(MessageType.ERROR, "Failed to update bank with new chip amount");
				}

				@Override
				public void onSuccess(UpdateChipEvent result) {
					Events.eventBus.fireEvent(result);
				}
				
			};
			
			service.updateChipCount(String.valueOf(user.getUserID()), amount, callback);
		}
	}
	
	/**
	 * @return true if the user has logged in and the user object is available
	 * false if otherwise
	 */
	public static boolean isUserLoggedIn() {
		return (user != null);
	}
	
	/**
	 * Checks the login status of the current user. If the user is not logged in
	 * return them to the Login Screen and display an info message to log back in.
	 */
	public static void checkLogin() {
		if (!isUserLoggedIn()) {
			dashboard.displayLoginScreen();
			dashboard.displayMessage(MessageType.INFO, 
					"You have been logged out, please log back in.");
		}
	}
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		UserController.user = user;
	}

	public static Dashboard getDashboard() {
		return dashboard;
	}

	public static void setDashboard(Dashboard dashboard) {
		UserController.dashboard = dashboard;
	}
	
	
}