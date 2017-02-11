package com.blackjack.shared.events;

import java.io.Serializable;

import com.blackjack.shared.handlers.UpdatePasswordHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UpdatePasswordEvent extends GwtEvent<UpdatePasswordHandler> implements Serializable{

	public static Type<UpdatePasswordHandler> TYPE = new Type<UpdatePasswordHandler>();
	
	private boolean isSuccess;
	private boolean isWrongCurrentPassword;
		
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UpdatePasswordHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdatePasswordHandler handler) {
		handler.onUpdatePassword(this);
	}

	public boolean isSuccess()
	{
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess)
	{
		this.isSuccess = isSuccess;
	}

	public boolean isWrongCurrentPassword() {
		return isWrongCurrentPassword;
	}

	public void setWrongCurrentPassword(boolean isWrongCurrentPassword) {
		this.isWrongCurrentPassword = isWrongCurrentPassword;
	}
	
	

}
