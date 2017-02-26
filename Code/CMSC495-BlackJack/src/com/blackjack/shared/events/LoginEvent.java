package com.blackjack.shared.events;

import java.io.Serializable;

import com.blackjack.shared.entities.User;
import com.blackjack.shared.handlers.LoginHandler;
import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginHandler> implements Serializable{
 
	public static Type<LoginHandler> TYPE = new Type<LoginHandler>();
	
	private User user;
	private boolean isSuccess;
	private boolean isPasswordInvalid;
	private boolean isUserNameInalid;
	private boolean isDailyBonus;
	private String errorString;
	
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
	public boolean isPasswordInvalid() {
		return isPasswordInvalid;
	}

	/**
	 * @param isInvalidPassword the isInvalidPassword to set
	 */
	public void setPasswordInvalid(boolean isInvalidPassword) {
		this.isPasswordInvalid = isInvalidPassword;
	}

	/**
	 * @return the isUsernameValid
	 */
	public boolean isUsernameInvalid() {
		return isUserNameInalid;
	}

	/**
	 * @param isUsernameValid the isUsernameValid to set
	 */
	public void setUsernameInvalid(boolean isUsernameValid) {
		this.isUserNameInalid = isUsernameValid;
	}

	public String getErrorString()	{
		return errorString;
	}

	public void setErrorString(String errorString)	{
		this.errorString = errorString;
	}

	public boolean isDailyBonus() {
		return isDailyBonus;
	}

	public void setDailyBonus(boolean isDailyBonus) {
		this.isDailyBonus = isDailyBonus;
	}
	
}
