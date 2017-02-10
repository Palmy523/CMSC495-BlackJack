package com.blackjack.shared.events;

import java.io.Serializable;

import com.blackjack.shared.handlers.ConfirmEmailHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ConfirmEmailEvent extends GwtEvent<ConfirmEmailHandler> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static Type<ConfirmEmailHandler> TYPE = new Type<ConfirmEmailHandler>();
	
	private boolean isSuccess;
	private boolean isEmailInvalid;
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ConfirmEmailHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ConfirmEmailHandler handler) {
		handler.onConfirmEmail(this);		
	}

	public boolean isSuccess()
	{
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess)
	{
		this.isSuccess = isSuccess;
	}

	public boolean isEmailInvalid()
	{
		return isEmailInvalid;
	}

	public void setEmailInvalid(boolean isEmailInvalid)
	{
		this.isEmailInvalid = isEmailInvalid;
	}
	
	

}
