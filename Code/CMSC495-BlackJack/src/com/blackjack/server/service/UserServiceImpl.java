package com.blackjack.server.service;

import com.blackjack.client.service.UserService;
import com.blackjack.shared.events.ConfirmEmailEvent;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateChipEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	
	@Override
	public LoginEvent login(String username, String password) {
		//TODO Check if the username and password are valid formats
		//using the FieldVerifier, if not return event with proper data.
		
		
		//TODO IF all good, call the login function from the UserController, create and return the LoginEvent with proper
		//data
		
		
		return null;
	}
	
	public CreateAccountEvent createAccount(String username, String password, String email) {
		//TODO Check if the username, password, and email are valid formats using the 
		//field verifier. If not, return event with proper data.
		
		
		//TODO IF all good, call the create account function from UserController, create and 
		//return the data 
		
		return null;
	}
	
	public UpdateChipEvent updateChipCount(String userID, int amount) {
		//TODO verify the update 
		
		//TODO IF all good, call the updateChipCount from UserControllerServer
		
		//TODO return the an UpdateChipEvent with proper data
		
		return null;
	}
	
	public ResetPasswordEvent resetPassword(String emailAddress) {
		//TODO verify email format use Field Verifier
		
		//TODO use UserControllerServer to check existing email
		
		//TODO if exists perform a password reset using the UserController Server
		
		//TODO send an email with temporary password to the email with EmailService
		
		//TODO create and return a ResetPasswordEvent with the proper data
		
		return null;
	}
	
	public UpdateEmailEvent updateEmail(String userID, String newEmail) {
		//TODO check that the user exists by userID
		
		//TODO put the newEmail address as the temp email
		
		//TODO create a temporary key and store in DB using controller
		
		//TODO send email with confirmation key
		
		//TODO return the UpdateEMailEvent with proper data
		return null;
	}
	
	public ConfirmEmailEvent confirmEmail(String userID, String confirmationKey) {
		//TODO check that user exists using controller
		
		//TODO check that the key matches the key in the DB for the userID using the controller
		
		//TODO perform the update if it does
		
		//TODO return ConfirmEmailEvent with proper data
		return null;
	}
	
	public UpdatePasswordEvent updatePassword(String userID, String currentPassword, String newPassword) {
		//TODO Encrypt currentPassword and check existing value in DB
		
		//TODO IF matches encrypt new password and update DB using controller
		
		//TODO create the UpdatePasswordEvent with proper data and return 
		return null;
	}
	
	

}
