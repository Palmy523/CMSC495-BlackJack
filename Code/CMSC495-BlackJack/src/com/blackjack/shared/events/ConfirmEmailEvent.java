package com.blackjack.shared.events;

import com.blackjack.shared.handlers.ConfirmEmailHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ConfirmEmailEvent extends GwtEvent<ConfirmEmailHandler>{

	public static Type<ConfirmEmailHandler> TYPE = new Type<ConfirmEmailHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ConfirmEmailHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ConfirmEmailHandler handler) {
		handler.onConfirmEmail(this);
		
	}

}