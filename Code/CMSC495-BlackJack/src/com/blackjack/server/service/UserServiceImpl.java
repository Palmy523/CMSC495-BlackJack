package com.blackjack.server.service;

import com.blackjack.client.service.UserService;
import com.blackjack.server.controller.UserControllerServer;
import com.blackjack.shared.FieldVerifier;
import com.blackjack.shared.FieldVerifier.FormatError;
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
		
		if(FieldVerifier.isValidUsername(username) == FormatError.LENGTH)
		{			
			loginEvent.setUsernameInvalid(true);
			loginEvent.setErrorString(FieldVerifier.USER_LENGTH_ERROR + "\r\n");
			loginEvent.setSuccess(false);
		}
		
		if(FieldVerifier.isValidUsername(username) == FormatError.INVALID_CHARACTER)
		{
			loginEvent.setUsernameInvalid(true);
			loginEvent.setErrorString(loginEvent.getErrorString() + " " + FieldVerifier.USER_REGEX_ERROR + "\r\n");
			loginEvent.setSuccess(false);
		}
		
		if(FieldVerifier.isValidPassword(password) != FormatError.LENGTH)
		{
			loginEvent.setPasswordInvalid(true);
			loginEvent.setErrorString(loginEvent.getErrorString() + " " + FieldVerifier.PASSWORD_LENGTH_ERROR + "\r\n");
			loginEvent.setSuccess(false);
		}
		
		if(FieldVerifier.isValidPassword(password) != FormatError.INVALID_CHARACTER)
		{
			loginEvent.setPasswordInvalid(true);
			loginEvent.setErrorString(loginEvent.getErrorString() + " " + FieldVerifier.PASSWORD_REGEX_ERROR);
			loginEvent.setSuccess(false);
		}
		
		if(loginEvent.isSuccess())
			loginEvent.setUser(UserControllerServer.login(username, password));

		return loginEvent;		
		
	}
	
	public CreateAccountEvent createAccount(String username, String password, String email) 
	{
		CreateAccountEvent createAccountEvent = new CreateAccountEvent();
		createAccountEvent.setSuccess(true);
		
		if(FieldVerifier.isValidUsername(username) == FormatError.LENGTH)
		{			
			createAccountEvent.setUserNameInvalid(true);
			createAccountEvent.setErrorString(FieldVerifier.USER_LENGTH_ERROR + "\r\n");
			createAccountEvent.setSuccess(false);
		}
		if(FieldVerifier.isValidUsername(username) == FormatError.INVALID_CHARACTER)
		{			
			createAccountEvent.setUserNameInvalid(true);
			createAccountEvent.setErrorString(createAccountEvent.getErrorString() + " " + FieldVerifier.USER_REGEX_ERROR + "\r\n");
			createAccountEvent.setSuccess(false);
		}
		
		if(UserControllerServer.userNameExists(username))
		{
			createAccountEvent.setUserNameInvalid(true);
			//TODO improve error message
			createAccountEvent.setErrorString(createAccountEvent.getErrorString() + " " + "userName exists" + "\r\n");
			createAccountEvent.setSuccess(false);
		}
			
		if(FieldVerifier.isValidPassword(password) != FormatError.LENGTH)
		{
			createAccountEvent.setPasswordInvalid(true);
			createAccountEvent.setErrorString(createAccountEvent.getErrorString() + " " + FieldVerifier.PASSWORD_LENGTH_ERROR + "\r\n");
			createAccountEvent.setSuccess(false);
		}
		if(FieldVerifier.isValidPassword(password) != FormatError.INVALID_CHARACTER)
		{
			createAccountEvent.setPasswordInvalid(true);
			createAccountEvent.setErrorString(createAccountEvent.getErrorString() + " " + FieldVerifier.PASSWORD_REGEX_ERROR + "\r\n");
			createAccountEvent.setSuccess(false);
		}
		
		if(FieldVerifier.isValidPassword(email) != FormatError.INVALID_FORMAT)
		{
			createAccountEvent.setEmailInvalid(true);
			createAccountEvent.setErrorString(createAccountEvent.getErrorString() + " " + FieldVerifier.EMAIL_ADDRESS_ERROR + "\r\n");
			createAccountEvent.setSuccess(false);
		}
		
		if(UserControllerServer.emailExists(email))
		{
			createAccountEvent.setEmailInvalid(true);
			//TODO improve error message
			createAccountEvent.setErrorString(createAccountEvent.getErrorString() + " " + "duplicate email" + "\r\n");
			createAccountEvent.setSuccess(false);
		}
		
		if(createAccountEvent.isSuccess())
			createAccountEvent.setUser(UserControllerServer.createAccount(username, password, email));

		return createAccountEvent;	
	}
	
	public UpdateChipEvent updateChipCount(String userID, int amount) {
		//TODO verify the update???
		
		UpdateChipEvent updateChipEvent = new UpdateChipEvent();
		updateChipEvent.setSuccess(true);
		
		
		if(updateChipEvent.isSuccess())
			UserControllerServer.updateChipCount(userID, amount);
		
		return null;
	}
	
	public ResetPasswordEvent resetPassword(String email) {
		//TODO verify email format use Field Verifier
		
		ResetPasswordEvent resetPasswordEvent = new ResetPasswordEvent();
		resetPasswordEvent.setSuccess(true);
		
		if(FieldVerifier.isValidEmail(email) != FormatError.INVALID_FORMAT)
		{
			resetPasswordEvent.setEmailInvalid(true);
			resetPasswordEvent.setErrorString(resetPasswordEvent.getErrorString() + " " + FieldVerifier.EMAIL_ADDRESS_ERROR + "\r\n");
			resetPasswordEvent.setSuccess(false);
		}
		
		if(UserControllerServer.emailExists(email))
		{
			UserControllerServer.resetPassword(email);
		}
		
		//TODO send an email with temporary password to the email with EmailService
		//resetPasswordEvent.setPasswordSendSuccess(true);
		
		return resetPasswordEvent;
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

	@Override
	public void initDB() {
		ConnectionService.initDB();
	}
	
	

}
