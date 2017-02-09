package com.blackjack.shared.events;

import java.io.Serializable;

import com.blackjack.shared.entities.User;
import com.blackjack.shared.handlers.CreateAccountHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event class for account creation events.
 * 
 * @author Dave
 *
 */
public class CreateAccountEvent extends GwtEvent<CreateAccountHandler> implements Serializable {

	private User user;
	private boolean isSuccess;
	private boolean isUserNameTaken;
	private boolean isUserNameInvalid;
	private boolean isEmailTaken;
	private boolean isEmailInvalid;
	private boolean isPasswordInvalid;
	private String errorString;
	
	public static Type<CreateAccountHandler> TYPE = new Type<CreateAccountHandler>();

	@Override
	public Type<CreateAccountHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateAccountHandler handler) {
		handler.onCreateAccount(this);
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
	 * @return the isUserNameTaken
	 */
	public boolean isUserNameTaken() {
		return isUserNameTaken;
	}

	/**
	 * @param isUserNameTaken the isUserNameTaken to set
	 */
	public void setUserNameTaken(boolean isUserNameTaken) {
		this.isUserNameTaken = isUserNameTaken;
	}

	/**
	 * @return the isUserNameInvalid
	 */
	public boolean isUserNameInvalid() {
		return isUserNameInvalid;
	}

	/**
	 * @param isUserNameInvalid the isUserNameInvalid to set
	 */
	public void setUserNameInvalid(boolean isUserNameInvalid) {
		this.isUserNameInvalid = isUserNameInvalid;
	}

	/**
	 * @return the isEmailTaken
	 */
	public boolean isEmailTaken() {
		return isEmailTaken;
	}

	/**
	 * @param isEmailTaken the isEmailTaken to set
	 */
	public void setEmailTaken(boolean isEmailTaken) {
		this.isEmailTaken = isEmailTaken;
	}

	/**
	 * @return the isEmailInvalid
	 */
	public boolean isEmailInvalid() {
		return isEmailInvalid;
	}

	/**
	 * @param isEmailInvalid the isEmailInvalid to set
	 */
	public void setEmailInvalid(boolean isEmailInvalid) {
		this.isEmailInvalid = isEmailInvalid;
	}

	/**
	 * @return the isPasswordInvalid
	 */
	public boolean isPasswordInvalid() {
		return isPasswordInvalid;
	}

	/**
	 * @param isPasswordInvalid the isPasswordInvalid to set
	 */
	public void setPasswordInvalid(boolean isPasswordInvalid) {
		this.isPasswordInvalid = isPasswordInvalid;
	}	

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	/**
	 * @return the tYPE
	 */
	public static Type<CreateAccountHandler> getTYPE() {
		return TYPE;
	}

	/**
	 * @param tYPE the tYPE to set
	 */
	public static void setTYPE(Type<CreateAccountHandler> tYPE) {
		TYPE = tYPE;
	}
	
	
}
