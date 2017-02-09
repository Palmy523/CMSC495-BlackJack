package com.blackjack.client.service;

import com.blackjack.shared.events.ConfirmEmailEvent;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateChipEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.user.client.rpc.RemoteService;

public interface UserService extends RemoteService {

	public LoginEvent login(String username, String password);
	
	public CreateAccountEvent createAccount(String username, String password, String email);

	public UpdateChipEvent updateChipCount(String userID, int amount);

	public ResetPasswordEvent resetPassword(String emailAddress);

	public UpdateEmailEvent updateEmail(String userID, String newEmail);
	
	public ConfirmEmailEvent confirmEmail(String userID, String confirmationKey);
	
	public UpdatePasswordEvent updatePassword(String userID, String currentPassword, String newPassword);
}
