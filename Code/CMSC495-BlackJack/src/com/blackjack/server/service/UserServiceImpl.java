package com.blackjack.server.service;

import com.blackjack.client.service.UserService;
import com.blackjack.server.controller.UserControllerServer;
import com.blackjack.shared.entities.User;
import com.blackjack.shared.events.ConfirmEmailEvent;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateChipEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public LoginEvent login(String username, String password) {
				
		LoginEvent loginEvent = new LoginEvent();
		loginEvent.setSuccess(false);
		
		if (!UserControllerServer.userNameExists(username)) {
			loginEvent.setUsernameInvalid(true);
			return loginEvent;
		}
		
		User user = UserControllerServer.login(username, password);
		if (user == null) {
			loginEvent.setPasswordInvalid(true);
			return loginEvent;
		}
		
		loginEvent.setSuccess(true);
		loginEvent.setUser(user);
		return loginEvent;		
		
	}
	
	public CreateAccountEvent createAccount(String username, String password, String email) 
	{
		CreateAccountEvent createAccountEvent = new CreateAccountEvent();
		createAccountEvent.setSuccess(true);
		
		if(UserControllerServer.userNameExists(username))
		{
			createAccountEvent.setUserNameInvalid(true);
			createAccountEvent.setErrorString("This user name is already in use." + "\r\n");
			createAccountEvent.setSuccess(false);
		}
			
		if(UserControllerServer.emailExists(email))
		{
			createAccountEvent.setEmailInvalid(true);
			createAccountEvent.setErrorString(createAccountEvent.getErrorString() + " " + "There is already an account that uses this email address." + "\r\n");
			createAccountEvent.setSuccess(false);
		}
		
		if(createAccountEvent.isSuccess())
			createAccountEvent.setUser(UserControllerServer.createAccount(username, password, email));

		return createAccountEvent;	
	}
	
	public UpdateChipEvent updateChipCount(String userID, int amount) 
	{
		UpdateChipEvent updateChipEvent = new UpdateChipEvent();
		updateChipEvent.setSuccess(true);
		
		if(!UserControllerServer.userExists(userID))
			updateChipEvent.setSuccess(false);
		
		if(updateChipEvent.isSuccess())
			UserControllerServer.updateChipCount(userID, amount);
		
		return updateChipEvent;
	}
	
	public ResetPasswordEvent resetPassword(String email) {
		
		ResetPasswordEvent resetPasswordEvent = new ResetPasswordEvent();
		resetPasswordEvent.setSuccess(true);
		
		if(!UserControllerServer.emailExists(email))
		{
			resetPasswordEvent.setSuccess(false);	
			resetPasswordEvent.setEmailInvalid(true);
			return resetPasswordEvent;
		}
		
		String tempPassword = null;
		if(resetPasswordEvent.isSuccess()) {
			tempPassword = UserControllerServer.resetPassword(email);
		}
		
		if (tempPassword == null || tempPassword.equals("")) {
			resetPasswordEvent.setSuccess(false);
			return resetPasswordEvent;
		}
		
		if (!EmailService.sendTemporaryPassword(email, tempPassword))
		{
			resetPasswordEvent.setPasswordSendSuccess(false);
		}
		
		return resetPasswordEvent;
	}
	
	public UpdateEmailEvent updateEmail(String userID, String newEmail) {
				
		UpdateEmailEvent updateEmailEvent = new UpdateEmailEvent();
		updateEmailEvent.setSuccess(true);
		
		if(!UserControllerServer.userExists(userID))
			updateEmailEvent.setSuccess(false);
		
		if (UserControllerServer.emailExists(newEmail)) {
			updateEmailEvent.setEmailInvalid(true);
			updateEmailEvent.setSuccess(false);
			return updateEmailEvent;
		}
		
		String confirmationKey = UserControllerServer.createTemporaryEmailUpdateKey(userID);
		if (confirmationKey == null) {
			updateEmailEvent.setSuccess(false);
			return updateEmailEvent;
		}
		
		if(!UserControllerServer.createTemporaryEmail(userID, newEmail))
			updateEmailEvent.setSuccess(false);

				
		if(!EmailService.sendEmailUpdateKey(newEmail, confirmationKey))
			updateEmailEvent.setSuccess(false);
		
		return updateEmailEvent;
	}
	
	public ConfirmEmailEvent confirmEmail(String userID, String confirmationKey) {
				
		ConfirmEmailEvent confirmEmailEvent = new ConfirmEmailEvent();
		
		if(!UserControllerServer.userExists(userID))
			confirmEmailEvent.setSuccess(false);
		
		
		if(!UserControllerServer.confirmationKeyMatches(confirmationKey))
			confirmEmailEvent.setSuccess(false);
		
		if(!UserControllerServer.performEmailUpdate(userID))
			confirmEmailEvent.setSuccess(false);
		
		return confirmEmailEvent;
	}
	
	public UpdatePasswordEvent updatePassword(String userID, String currentPassword, String newPassword) {
		
		UpdatePasswordEvent updatePasswordEvent = new UpdatePasswordEvent();
		updatePasswordEvent.setSuccess(true);
		
		//Make sure the user exists
		if (!UserControllerServer.userExists(userID)) {
			updatePasswordEvent.setSuccess(false);
			return updatePasswordEvent;
		}
		
		//Check current password validity
		if (!UserControllerServer.verifyPassword(userID, currentPassword)) {
			updatePasswordEvent.setWrongCurrentPassword(true);
			updatePasswordEvent.setSuccess(false);
			return updatePasswordEvent;
		}
		
		updatePasswordEvent.setSuccess(
				UserControllerServer.updatePassword(userID, newPassword));
		
		return updatePasswordEvent;
	}

	@Override
	public void initDB() {
		ConnectionService.initDB();
	}
}
