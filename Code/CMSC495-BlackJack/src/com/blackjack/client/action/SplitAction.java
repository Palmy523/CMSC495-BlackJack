package com.blackjack.client.action;

import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;

public class SplitAction extends GameAction {

	public SplitAction(BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(GameEvent event) {
		// TODO Auto-generated method stub
		
		panel.getSplitButton().setEnabled(false);
		
		//Splits players hand into two separate hands
		panel.splitPlayerHand();
		event.getGameState().setSplit(true);
		
		event.setActionType(ActionType.SPLIT);
		Events.eventBus.fireEvent(event);
	}

}
