package com.blackjack.client.controls;

import com.blackjack.client.action.BetAction;
import com.blackjack.client.action.DealAction;
import com.blackjack.client.action.GameAction;
import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.action.HitAction;
import com.blackjack.client.action.StandAction;
import com.blackjack.client.entities.Deck;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.ui.Dashboard;

public class GameController {

	private Dashboard dashboard;
	private BlackJackGamePanel gamePanel;
	private GameState gameState;

	public GameController(Dashboard dashboard, GameState gameState) {
		this.dashboard = dashboard;
		gamePanel = dashboard.getGamePanel();
		this.gameState = gameState;
		
		GameAction onHandEnd = new GameAction(gamePanel) {

			@Override
			public void processAction(GameEvent event) {
				if (event.getActionType() == ActionType.HAND_END) {
					startGame();
				}
			}
		};
		
		Events.eventBus.addHandler(GameEvent.TYPE, onHandEnd);
	}

	/**
	 * Starts a game of 21!
	 * 
	 * @param room
	 * @param isEasyPlay
	 */
	public void startGame() {
		if (GameState.getDeck().getCardUsage() < .75f) {
			Deck original = GameState.getDeck();
			Deck newDeck = new Deck(original.getSet(), original.getNumberOfDecks(), true);
			GameState.setDeck(newDeck);
		}
		GameState.setBetAmount(0);
		GameState.setDealerHand(new Hand());
		GameState.setPlayerHand(new Hand());
		GameState.setTurn(TurnState.AWAITING_BET);
		dashboard.displayGamePanel();
		gamePanel.reset();
		
		GameState.setTurn(TurnState.AWAITING_BET);
	}

	public void quitGame() {
		// TODO quit the game and return to the RoomSelectionScreen
	}

	public void deal() {
		// Create a GameEvent with the current GameState.
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.DEAL);
		// Create a new DealAction
		DealAction action = new DealAction(100, gamePanel);
		// Invoke the processAction method of the DealAction
		action.processAction(event);
	}

	public void playerHit() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.HIT);
		HitAction action = new HitAction(gamePanel);
		action.processAction(event);
	}

	public void dealerHit() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.HIT);
		HitAction action = new HitAction(gamePanel);
		action.processAction(event);
	}

	public void playerStand() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.STAND);
		StandAction action = new StandAction(gamePanel);
		action.processAction(event);
	}

	public void insurance() {
		// TODO invokes insurance for the player
	}

	public void doubleDown() {
		// TODO cause the player to Double Down
	}

	public void split() {
		// TODO splits the players hand

	}

	public void surrender() {
		// TODO causes the player to surrender
	}

	public void betPlus(int amount) {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.BET);
		BetAction action = new BetAction(gamePanel, amount);
		action.processAction(event);
	}

	public void betMinus(int amount) {
		// TODO decrease the bet by the specified amount
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

}
