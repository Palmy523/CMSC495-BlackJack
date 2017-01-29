package com.blackjack.server.service;

import com.blackjack.client.service.UserService;
import com.blackjack.shared.entities.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {

	@Override
	public User login(String username, String password) {
		//validate information with info in database. Create the user and return it
		return null;
	}

}
