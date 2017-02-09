package com.blackjack.shared.events;

import java.io.Serializable;

import com.blackjack.shared.handlers.UpdatePasswordHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UpdatePasswordEvent extends GwtEvent<UpdatePasswordHandler> implements Serializable{

	public static Type<UpdatePasswordHandler> TYPE = new Type<UpdatePasswordHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UpdatePasswordHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdatePasswordHandler handler) {
		handler.onUpdatePassword(this);
	}

}
