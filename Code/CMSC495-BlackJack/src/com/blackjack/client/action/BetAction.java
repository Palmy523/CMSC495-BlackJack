package com.blackjack.client.action;

import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.sounds.SoundManager;
import com.blackjack.client.sounds.SoundManager.SoundName;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.ui.GameButton.GameButtonType;
import com.google.gwt.core.client.GWT;

/**
 * The BetAction class performs a bet in the game. It is called
 * and executed each time a bet is placed.
 * 
 * @author Stephanie
 *
 */
public class BetAction extends GameAction {
	private int betAmount;

	/**
	 * Constructor
	 * @param panel the BlackJackGamePanel to update
	 * @param betAmount the amount to update the bet by
	 */
	public BetAction(BlackJackGamePanel panel, int betAmount) {
		super(panel);
		this.betAmount = betAmount;
	}

	/**
	 * Processes the bet
	 */
	@Override
	public void processAction(GameEvent event) {

		if (GameState.getTurn() != TurnState.AWAITING_BET &&
				GameState.getTurn() != TurnState.AWAITING_DEAL
				|| GameState.getBetAmount() > GameState.getRoom().getMaxBet()) {
			return;
		}

		int currentBet = GameState.getBetAmount() + betAmount;
		if (currentBet < 0) {
			currentBet = 0;
		}
		GameState.setBetAmount(currentBet);

		SoundManager.play(SoundName.CHIP_BET);

		if (currentBet >= GameState.getRoom().getMinBet()) {
			panel.enableButton(GameButtonType.DEAL, true);
			GameState.setTurn(TurnState.AWAITING_DEAL);
		}

		if (currentBet >= GameState.getRoom().getMaxBet()) {
			currentBet = GameState.getRoom().getMaxBet();
			GameState.setBetAmount(currentBet);
		}
		
		if (currentBet < GameState.getRoom().getMinBet()) {
			GameState.setBetAmount(currentBet);
			panel.chipsEnabled(true);
			panel.enableButton(GameButtonType.DEAL, false);
			GameState.setTurn(TurnState.AWAITING_BET);
		}

		panel.bet(currentBet);

		// Fire the event so the rest of the UI knows that the action occurred
		event.setActionType(ActionType.BET);
		Events.eventBus.fireEvent(event);
	}
}
