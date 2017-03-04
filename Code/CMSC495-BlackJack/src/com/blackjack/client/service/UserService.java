package com.blackjack.client.service;

import com.blackjack.shared.events.ConfirmEmailEvent;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateChipEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Abstract class used to instantiate a remote service class for communication 
 * with the server.
 * @author Dave
 *
 */
@RemoteServiceRelativePath("UserService")
public interface UserService extends RemoteService {
	
	public LoginEvent login(String username, String password);
	
	public CreateAccountEvent createAccount(String username, String password, String email);

	public UpdateChipEvent updateChipCount(String userID, float amount);

	public ResetPasswordEvent resetPassword(String emailAddress, String tempPassword);

	public UpdateEmailEvent updateEmail(String userID, String newEmail, String tempKey);
	
	public ConfirmEmailEvent confirmEmail(String userID, String confirmationKey);
	
	public UpdatePasswordEvent updatePassword(String userID, String currentPassword, String newPassword);
}
