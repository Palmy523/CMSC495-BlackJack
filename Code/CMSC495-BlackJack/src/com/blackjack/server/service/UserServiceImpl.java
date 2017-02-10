package com.blackjack.server.service;

import com.blackjack.client.service.UserService;
import com.blackjack.server.controller.UserControllerServer;
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
		loginEvent.setSuccess(true);
		
		
		if(loginEvent.isSuccess())
			loginEvent.setUser(UserControllerServer.login(username, password));

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
		}
		
		if(resetPasswordEvent.isSuccess())
			UserControllerServer.resetPassword(email);
		
		String tempPassword = UserControllerServer.createRandomKey();
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
		
		String confirmationKey = UserControllerServer.createTemporaryEmailUpdateKey(userID);
		
		if(!UserControllerServer.createTemporaryEmail(userID, newEmail))
			updateEmailEvent.setSuccess(false);

				
		if(!EmailService.sendEmailUpdateKey(newEmail, confirmationKey))
			updateEmailEvent.setSuccess(false);
		
		if(!UserControllerServer.performEmailUpdate(userID))
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
				
		//TODO Encrypt currentPassword and check existing value in DB
		
		//TODO IF matches encrypt new password and update DB using controller
		
		//TODO create the UpdatePasswordEvent with proper data and return 
		return null;
	}

	@Override
	public void initDB() {
		ConnectionService.initDB();
	}
}
