package com.blackjack.server.service;

import java.sql.Date;
import java.util.Calendar;

import com.blackjack.client.service.UserService;
import com.blackjack.server.controller.UserControllerServer;
import com.blackjack.shared.CompareDates;
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

		Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		if(user.getLastLogin() == null || !CompareDates.isSameDay(user.getLastLogin(), currentDate)) {
			user.setBankAmount(user.getBankAmount() + 250f);
			this.updateChipCount(String.valueOf(user.getUserID()), 250f);
		}
		
		user.setLastLogin(UserControllerServer.updateLastLogin(username));
		
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
			createAccountEvent.setUserNameTaken(true);
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
	
	public UpdateChipEvent updateChipCount(String userID, float amount) 
	{
		UpdateChipEvent updateChipEvent = new UpdateChipEvent();
		updateChipEvent.setSuccess(true);
		
		if(!UserControllerServer.userExists(userID))
			updateChipEvent.setSuccess(false);
		
		if(updateChipEvent.isSuccess()) { 
			updateChipEvent.setNewAmount(UserControllerServer.updateChipCount(userID, amount));
		}
			
		return updateChipEvent;
	}
	
	public ResetPasswordEvent resetPassword(String email, String tempPassword) {
		
		ResetPasswordEvent resetPasswordEvent = new ResetPasswordEvent();
		resetPasswordEvent.setSuccess(true);
		
		if(!UserControllerServer.emailExists(email))
		{
			resetPasswordEvent.setSuccess(false);	
			resetPasswordEvent.setEmailInvalid(true);
			return resetPasswordEvent;
		}
		
		if(resetPasswordEvent.isSuccess()) {
			boolean success = UserControllerServer.resetPassword(email, tempPassword);
			resetPasswordEvent.setSuccess(success);
		}
		
		
		if (!EmailService.sendTemporaryPassword(email, tempPassword))
		{
			resetPasswordEvent.setPasswordSendSuccess(false);
		}
		
		return resetPasswordEvent;
	}
	
	public UpdateEmailEvent updateEmail(String userID, String newEmail, String tempKey) {
				
		UpdateEmailEvent updateEmailEvent = new UpdateEmailEvent();
		updateEmailEvent.setSuccess(true);
		
		if(!UserControllerServer.userExists(userID))
			updateEmailEvent.setSuccess(false);
		
		if (UserControllerServer.emailExists(newEmail)) {
			updateEmailEvent.setEmailInvalid(true);
			updateEmailEvent.setSuccess(false);
			return updateEmailEvent;
		}
		
		boolean success = 
				UserControllerServer.createTemporaryEmailUpdateKey(userID, tempKey);
		updateEmailEvent.setSuccess(success);
		
		if(!UserControllerServer.createTemporaryEmail(userID, newEmail))
			updateEmailEvent.setSuccess(false);

				
		if(!EmailService.sendEmailUpdateKey(newEmail, tempKey))
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
}
