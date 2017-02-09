package com.blackjack.client.controls;

import com.blackjack.shared.events.ConfirmEmailEvent;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;

public class UserController {

	public UserController() {}
	
	public void createAccount(String username, 
			String password, 
			String confirmPassword, 
			String email) {
		
		//TODO Verify the inputs using FieldVerifier
		
		//TODO Call UserService create account
	}
	
	public void OnCreateAccountSuccess() {
		//TODO Display Login Screen prompt user to re-enter their credentials
		
	}
	
	public void OnCreateAccountFailure(CreateAccountEvent event) {
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
