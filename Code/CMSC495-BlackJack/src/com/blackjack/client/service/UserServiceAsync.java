package com.blackjack.client.service;

import com.blackjack.shared.events.ConfirmEmailEvent;
import com.blackjack.shared.events.CreateAccountEvent;
import com.blackjack.shared.events.LoginEvent;
import com.blackjack.shared.events.ResetPasswordEvent;
import com.blackjack.shared.events.UpdateChipEvent;
import com.blackjack.shared.events.UpdateEmailEvent;
import com.blackjack.shared.events.UpdatePasswordEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void initDB(AsyncCallback<Void> callback);
	void login(String username, String password, AsyncCallback<LoginEvent> callback);
	void createAccount(String username, String password, String email, AsyncCallback<CreateAccountEvent> callbacks);
	void updateChipCount(String userID, int amount, AsyncCallback<UpdateChipEvent> callback);
	void resetPassword(String emailAddress, AsyncCallback<ResetPasswordEvent> callback);
	void updateEmail(String userID, String newEmail, AsyncCallback<UpdateEmailEvent> callback);
	void confirmEmail(String userID, String confirmationKey, AsyncCallback<ConfirmEmailEvent> callback);
	void updatePassword(String userID, String currentPassword, String newPassword, AsyncCallback<UpdatePasswordEvent> callback);
}
