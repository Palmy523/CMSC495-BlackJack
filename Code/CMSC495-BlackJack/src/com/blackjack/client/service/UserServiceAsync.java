package com.blackjack.client.service;

import com.blackjack.shared.events.ConfirmEmailEvent;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateChipEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * An async interface used to instantiate a service class for communication with the 
 * server and define a callback method to perform based on the server response.
 * @author Dave
 *
 */
public interface UserServiceAsync {

	void login(String username, String password, AsyncCallback<LoginEvent> callback);
	void createAccount(String username, String password, String email, AsyncCallback<CreateAccountEvent> callbacks);
	void updateChipCount(String userID, float amount, AsyncCallback<UpdateChipEvent> callback);
	void resetPassword(String emailAddress, String tempPassword, AsyncCallback<ResetPasswordEvent> callback);
	void updateEmail(String userID, String newEmail, String tempKey, AsyncCallback<UpdateEmailEvent> callback);
	void confirmEmail(String userID, String confirmationKey, AsyncCallback<ConfirmEmailEvent> callback);
	void updatePassword(String userID, String currentPassword, String newPassword, AsyncCallback<UpdatePasswordEvent> callback);
}
