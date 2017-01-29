package com.blackjack.client.event;

import com.blackjack.shared.entities.User;

public class LoginEvent {

	private User user;
	private boolean isSuccess;
	
	public LoginEvent() {};
}
