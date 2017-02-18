package com.blackjack.client.event;

import com.blackjack.client.action.GameAction;
import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.entities.GameState;
import com.google.gwt.event.shared.GwtEvent;

public class GameEvent extends GwtEvent<GameAction> {

	public static Type<GameAction> TYPE = new Type<GameAction>();	
	private GameState gameState;
	private ActionType actionType;
	
	
	public GameEvent(GameState gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public Type getAssociatedType() {
		return TYPE;
	}
	
	@Override
	protected void dispatch(GameAction handler) {
		handler.processAction(this);
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	
	
	
}