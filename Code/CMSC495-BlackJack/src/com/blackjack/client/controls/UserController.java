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
	
	public void createAccount(String username, 
			String password, 
			String confirmPassword, 
			String email) {
		
		if (FieldVerifier.isValidEmail(email) == FormatError.INVALID_FORMAT) {
			dashboard.displayMessage(MessageType.ERROR, FieldVerifier.EMAIL_ADDRESS_ERROR);
		}
		
		if (FieldVerifier.isValidPassword(password) == FormatError.LENGTH) {
			dashboard.displayMessage(MessageType.ERROR, FieldVerifier.PASSWORD_LENGTH_ERROR);
		}
		
		if (FieldVerifier.isValidPassword(password) == FormatError.INVALID_FORMAT) {
			dashboard.displayMessage(MessageType.ERROR, FieldVerifier.PASSWORD_REGEX_ERROR);
		}
		
		if (FieldVerifier.isValidEmail(email) == FormatError.INVALID_FORMAT) {
			dashboard.displayMessage(MessageType.ERROR, FieldVerifier.EMAIL_ADDRESS_ERROR);
		}
		
		if (!password.equals(confirmPassword)) {
			dashboard.displayMessage(MessageType.ERROR, "Sorry, the passwords you provided do not match.");
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
		//TODO Display Login Screen prompt user to re-enter their credentials
		
	}
	
	public void onCreateAccountFailure(CreateAccountEvent event) {
		//TODO Display error message to user with the appropriate message
	}
	
	public void resetPassword(String emailAddress) {
		//TODO very email address format is correct
		
		//Call the UserService to reset the password
		
	}
	
	public void onResetPasswordSuccess() {
		//TODO Display success message
	}
	
	public void onResetPasswordFailure(ResetPasswordEvent event) {
		//TODO Display error message
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
	
	public void updatePassword(String userID, String currentPassword, String oldPassword) {
		//TODO verify password formats are correct
		//TODO send action to userservice
	}
	
	public void onUpdatePasswordSuccess() {
		//TODO display success message
	}
	
	public void onUpdatePasswordFailure(UpdatePasswordEvent event) {
		//TODO display error message
	}
	
}
