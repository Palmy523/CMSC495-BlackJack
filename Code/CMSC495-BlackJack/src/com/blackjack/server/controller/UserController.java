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
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UserController {

	private Dashboard dashboard;
	private User user;
	
	public UserController(Dashboard dashboard) {}
	
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
			//dashboard.displayMessage(MessageType.INFO, "Sorry, the passwords you provided do not match.");
		}
		
		UserServiceAsync userService = GWT.create(UserService.class);
		AsyncCallback<CreateAccountEvent> callback = new AsyncCallback<CreateAccountEvent>() {

			@Override
			public void onFailure(Throwable caught) {
				dashboard.displayMessage(MessageType.ERROR, "Sorry, there was a communication problem with the "
						+ "server. Please try again later.");
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
		
		userService.createAccount(username, confirmPassword, email, callback);
	}
	
	public void onCreateAccountSuccess(CreateAccountEvent event) {
		//DONE - TODO Display Login Screen prompt user to re-enter their credentials
                dashboard.displayLoginScreen();
			dashboard.displayMessage(MessageType.INFO, 
					"Re-enter your credentials to log in!");
		
	}
	
	public void onCreateAccountFailure(CreateAccountEvent event) {
		//DONE - TODO Display error message to user with the appropriate message
                //Is the error message enough to display?
                if(event.isUserNameTaken()){
                    dashboard.displayMessage(MessageType.INFO, 
			"Account creation failed - this username is already taken!");
                }
                if(event.isUserNameInvalid()){
                    dashboard.displayMessage(MessageType.INFO, 
			"Account creation failed - this username is invalid!");
                }
                if(event.isEmailTaken()){
                    dashboard.displayMessage(MessageType.INFO, 
			"Account creation failed - this email has already been used!");
                }
                if(event.isEmailInvalid()){
                    dashboard.displayMessage(MessageType.INFO, 
			"Account creation failed - this email is invalid!");
                }
                if(event.isPasswordInvalid()){
                    dashboard.displayMessage(MessageType.INFO, 
			"Account creation failed - this password is invalid!");
                }
	}
	
	public void resetPassword(String emailAddress) {
		//DONE - TODO verify user is logged in
                    checkLogin();
		//TODO verify email address format is correct
                    if(!emailAddress.matches("[a-zA-Z0-9!@#$%^&*()_]+@+[a-zA-Z]+.+[a-zA-Z]")){
                        //DONE - generate error
                        dashboard.displayMessage(MessageType.INFO, 
			"This email is invalid!");
                    }
                    else{
                        //DONE - TODO Call the UserService to reset the password
                            UserService.ResetPassword(emailAddress);
                    }
	}
	
	public void onResetPasswordSuccess() {
		//DONE - TODO Display success message 
                    dashboard.displayMessage(MessageType.INFO, 
					"Password successfully reset!");
	}
	
	public void onResetPasswordFailure(ResetPasswordEvent event) {
		//DONE - TODO Display error message
                    dashboard.displayMessage(MessageType.INFO, 
					"Password reset failed. Please try again later.");
	}
	
	public void updatEmail(String userID, String newEmailAddress) {
		//DONE - TODO verify new email format
                    if(!newEmailAddress.matches("[a-zA-Z0-9!@#$%^&*()_]+@+[a-zA-Z]+.+[a-zA-Z]"))	
                    {
                        dashboard.displayMessage(MessageType.INFO, 
			"Cannot update email - new email address is invalid!");
                    }
                    else{
                     //DONE - TODO call the UserService to perform the update  
                        UserService.UpdateEmail(userID, newEmailAddress);
                    }
	}
	
	public void onUpdateEmailSuccess() {
		//TODO display the confirmation text box for entry of the 
		//confirmation key sent to email
	}
	
	public void onUpdateEmailFailure(UpdateEmailEvent event) {
		//DONE - TODO display error
                    dashboard.displayMessage(MessageType.INFO, 
					"Email update failed!");
	}
	
	
	public void confirmEmail(String userID, String confirmationKey) {
		//TODO very confirmation key format
		if(true){
                    
                }
                else{
                 //DONE - TODO call the UserService   
                    UserService.confirmEmail(userID,confirmationKey);
                }
	}
	
	public void onConfirmEmailSuccess() {
		//TODO display success message, remove confirmation screen
	}
	
	public void onConfirmEmailFailure(ConfirmEmailEvent event) {
		//TODO display an error message
                dashboard.displayMessage(MessageType.INFO, 
					"Email update failed!");
	}
	
	public void updatePassword(String userID, String currentPassword, String oldPassword) {
		checkLogin();
		//DONE - TODO verify password formats are correct -Password: over 8 characters, A-Z, 0-9, !@#$%^&*()
                    if(currentPassword.length() < 8){
                        //DONE - send update password failure - password too short
                        dashboard.displayMessage(MessageType.INFO, 
					"Password is too short! Minimum: 8 characters");
                    }
                    else if(!currentPassword.matches("[a-zA-Z0-9!@#$%^&*()]+")){
                        //DONE - send update password failure - password contains illegal characters
                        dashboard.displayMessage(MessageType.INFO, 
					"Password contains illegal character! Passwords may contain: A-z, 0-9,!@#$%^&*() ");
                    }
                    else{
                        //DONE - TODO send action to userservice                        
                        UserService.updatePassword(userID, oldPassword, currentPassword);
                    }
	}
	
	public void onUpdatePasswordSuccess(UpdatePasswordEvent event) {
		//DONE - TODO display success message  
                dashboard.displayMessage(MessageType.INFO, 
					"Password successfully updated!");
	}
	
	public void onUpdatePasswordFailure(UpdatePasswordEvent event) {
		//DONE - TODO display error message    
                dashboard.displayMessage(MessageType.INFO, 
					"Password update failed!");                
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
