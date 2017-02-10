package com.blackjack.shared.events;

import java.io.Serializable;

import com.blackjack.shared.handlers.UpdateEmailHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UpdateEmailEvent extends GwtEvent<UpdateEmailHandler> implements Serializable {

	public static Type<UpdateEmailHandler> TYPE = new Type<UpdateEmailHandler>();
	
	private boolean isSuccess;
	private boolean isEmailInvalid;
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UpdateEmailHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateEmailHandler handler) {
		handler.onUpdateEmail(this);
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
