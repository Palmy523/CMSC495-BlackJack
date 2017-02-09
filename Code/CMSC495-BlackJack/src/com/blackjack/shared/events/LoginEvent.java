package com.blackjack.shared.events;

import com.blackjack.shared.entities.User;
import com.blackjack.shared.handlers.LoginHandler;
import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginHandler>{
 
	public static Type<LoginHandler> TYPE = new Type<LoginHandler>();
	
	private User user;
	private boolean isSuccess;
	private boolean isInvalidPassword;
	private boolean isUsernameValid;
	
	public LoginEvent() {}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoginHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoginHandler handler) {
		handler.OnLogin(this);
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	/**
	 * @return the isInvalidPassword
	 */
	public boolean isInvalidPassword() {
		return isInvalidPassword;
	}

	/**
	 * @param isInvalidPassword the isInvalidPassword to set
	 */
	public void setInvalidPassword(boolean isInvalidPassword) {
		this.isInvalidPassword = isInvalidPassword;
	}

	/**
	 * @return the isUsernameValid
	 */
	public boolean isUsernameValid() {
		return isUsernameValid;
	}

	/**
	 * @param isUsernameValid the isUsernameValid to set
	 */
	public void setUsernameValid(boolean isUsernameValid) {
		this.isUsernameValid = isUsernameValid;
	};
	
	
	
	
}
