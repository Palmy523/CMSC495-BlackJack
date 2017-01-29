package com.blackjack.client.service;

import com.blackjack.shared.entities.User;
import com.google.gwt.user.client.rpc.RemoteService;

public interface UserService extends RemoteService {

	public User login(String username, String password);
}
