package com.blackjack.client.event;

import com.blackjack.client.action.GameAction;
import com.google.gwt.event.shared.GwtEvent;

public class GameEvent extends GwtEvent<GameAction> {

	public Type<GameAction> TYPE = new Type<GameAction>();	
	
	@Override
	public Type getAssociatedType() {
		return TYPE;
	}
	
	@Override
	protected void dispatch(GameAction handler) {
		handler.processAction(this);
	}
	
}