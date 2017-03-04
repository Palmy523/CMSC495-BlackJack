package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
import com.blackjack.client.ui.BlackJackGamePanel;

/**
 * Action used to process a stand for a hand.
 * 
 * @author Abby
 *
 */
public class StandAction extends GameAction {

	/**
	 * 
	 * @param panel the BlackJackGamePanel to update.
	 */
	public StandAction(BlackJackGamePanel panel) {
		super(panel);
	}

	/**
	 * Causes the state of the hand to go into a stand state. Updates UI
	 * by displaying the stand label. Ends turns and procs dealers turn or 
	 * HandEnd appropriately.
	 */
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
								
				DealerTurnAction action = new DealerTurnAction(panel);
				action.processAction(event);
			} else if (GameState.isSplit() && GameState.isHittingPrimary()) {
				panel.playerStand();
				SoundManager.play(SoundName.STAND);
				GameState.setHittingPrimary(false);
				GameState.setHittingSplit(true);
				HitAction h = new HitAction(panel);
				h.processAction(event);
			}
		} else if (GameState.getTurn() == TurnState.DEALER_TURN) {
			panel.dealerStand();
			SoundManager.play(SoundName.STAND);
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
