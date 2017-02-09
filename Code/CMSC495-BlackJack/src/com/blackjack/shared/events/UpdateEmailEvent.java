package com.blackjack.shared.events;

import com.blackjack.shared.handlers.UpdateEmailHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UpdateEmailEvent extends GwtEvent<UpdateEmailHandler> {

	public static Type<UpdateEmailHandler> TYPE = new Type<UpdateEmailHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UpdateEmailHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateEmailHandler handler) {
		handler.onUpdateEmail(this);
	}

}
