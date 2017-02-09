package com.blackjack.shared.events;

import com.blackjack.shared.handlers.ResetPasswordHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ResetPasswordEvent extends GwtEvent<ResetPasswordHandler> {

	public static Type<ResetPasswordHandler> TYPE = new Type<ResetPasswordHandler>();

	private boolean isSuccess;
	private boolean isEmailValid;
	private boolean isPasswordSendSuccess;
	
	@Override
	public Type<ResetPasswordHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ResetPasswordHandler handler) {
		handler.onResetPassword(this);
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
	 * @return the isEmailValid
	 */
	public boolean isEmailValid() {
		return isEmailValid;
	}

	/**
	 * @param isEmailValid the isEmailValid to set
	 */
	public void setEmailValid(boolean isEmailValid) {
		this.isEmailValid = isEmailValid;
	}

	/**
	 * @return the isPasswordSendSuccess
	 */
	public boolean isPasswordSendSuccess() {
		return isPasswordSendSuccess;
	}

	/**
	 * @param isPasswordSendSuccess the isPasswordSendSuccess to set
	 */
	public void setPasswordSendSuccess(boolean isPasswordSendSuccess) {
		this.isPasswordSendSuccess = isPasswordSendSuccess;
	}
	
	

}
