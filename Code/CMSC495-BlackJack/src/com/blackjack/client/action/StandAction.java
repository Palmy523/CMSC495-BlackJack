package com.blackjack.client.action;

import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
import com.blackjack.client.ui.BlackJackGamePanel;

public class StandAction extends GameAction {

	public StandAction(BlackJackGamePanel panel) {
		super(panel);
	}

	@Override
	public void processAction(GameEvent event) {
		if (GameState.getTurn() == TurnState.PLAYER_TURN) {
			if (!GameState.isSplit() || (GameState.isSplit() && GameState.isHittingSplit())) {
				if (GameState.isHittingSplit()) {
					panel.playerStand_Split();
				} else {
					panel.playerStand();
				}
				
				panel.disableAllButtons();
				SoundManager.play(SoundName.STAND);
				GameState.setTurn(TurnState.DEALER_TURN);
				
				GameState.setHittingPrimary(true);
				GameState.setHittingSplit(false);
				GameState.setSplit(false);
				
				DealerTurnAction action = new DealerTurnAction(panel);
				action.processAction(event);
			} else if (GameState.isSplit() && GameState.isHittingPrimary()) {
				panel.playerStand();
				GameState.setHittingPrimary(false);
				GameState.setHittingSplit(true);
			}
		} else if (GameState.getTurn() == TurnState.DEALER_TURN) {
			panel.dealerStand();
			GameState.setTurn(TurnState.HAND_END);

			// create HandEndAction-implement and end action
			HandEndAction action = new HandEndAction(panel);
			action.processAction(event);
		}

		// Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.STAND);
		Events.eventBus.fireEvent(event);
	}

}
