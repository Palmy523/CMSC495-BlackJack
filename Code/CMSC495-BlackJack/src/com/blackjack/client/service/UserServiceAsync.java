package com.blackjack.client.service;

import com.blackjack.shared.entities.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void login(String username, String password, AsyncCallback<User> callback);

}
