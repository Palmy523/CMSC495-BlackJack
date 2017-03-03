package com.blackjack.client.controls;

import com.blackjack.client.action.BetAction;
import com.blackjack.client.action.DealAction;
import com.blackjack.client.action.DeclineInsuranceAction;
import com.blackjack.client.action.DoubleDownAction;
import com.blackjack.client.action.GameAction;
import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.action.HitAction;
import com.blackjack.client.action.InsuranceAction;
import com.blackjack.client.action.SplitAction;
import com.blackjack.client.action.StandAction;
import com.blackjack.client.action.SurrenderAction;
import com.blackjack.client.entities.Deck;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.entities.Hand;
import com.blackjack.client.event.Events;
import com.blackjack.client.event.GameEvent;
import com.blackjack.client.ui.BlackJackGamePanel;
import com.blackjack.client.ui.Dashboard;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;

public class GameController {

	private Dashboard dashboard;
	private BlackJackGamePanel gamePanel;
	private GameState gameState;
	private HandlerRegistration registration;
	private UserController userController;

	public GameController(Dashboard dashboard, GameState gameState) {
		this.dashboard = dashboard;
		gamePanel = dashboard.getGamePanel();
		this.gameState = gameState;
		
		GameAction onHandEnd = new GameAction(gamePanel) {

			@Override
			public void processAction(GameEvent event) {
				if (event.getActionType() == ActionType.HAND_END) {
					startGame();
					
					if(GameState.getUser().getBankAmount() < GameState.getRoom().getChipLimit())
					{
						panel.displayInstruction("You no longer meet the requirements to play in this room. Please exit the room.");
						panel.disableAllButtons();
						panel.chipsEnabled(false);
					}
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
		GameState.setDoubledDown(false);
		GameState.setInsurance(false);
		GameState.setSurrender(false);
		GameState.setSplit(false);
		dashboard.displayGamePanel();
		gamePanel.reset();
		
		Button quitButton = dashboard.getMessageBox().getQuitButton();
		
		registration = 
				quitButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {				
				dashboard.getMessageBox().setVisible(false);	
				dashboard.getMessageBox().getQuitButton().setVisible(false);
				registration.removeHandler();
				quitGame();				
			}
			
		});
		
		GameState.setTurn(TurnState.AWAITING_BET);
		
//		//TEMP for testing
//		gamePanel.getSplitButton().setEnabled(true);
	}

	/**
	 * Quits the game, returns to the RoomSelectionScreen, and resets the GamePanel UI
	 */
	public void quitGame() {
		// TODO lose chips
		gamePanel.reset();
		gamePanel.resetHands();
		GameState.setTurn(TurnState.AWAITING_BET);
		gamePanel.displayInstruction("Place bet");
		dashboard.displayRoomSelectionScreen();
	}

	/**
	 * Calls the deal action to deal out cards to the player and dealer.
	 */
	public void deal() {
		// Create a GameEvent with the current GameState.
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.DEAL);
		// Create a new DealAction
		DealAction action = new DealAction(100, gamePanel);
		// Invoke the processAction method of the DealAction
		action.processAction(event);
	}

	/**
	 * Calls the hit action to hit the player hand with a single card
	 */
	public void playerHit() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.HIT);
		HitAction action = new HitAction(gamePanel);
		action.processAction(event);
	}
	
	/**
	 * Calls the hit action to hit the dealer hand with a single card
	 */
	public void dealerHit() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.HIT);
		HitAction action = new HitAction(gamePanel);
		action.processAction(event);
	}

	/**
	 * Calls the Stand Action to stop the player turn and push off the dealer turn
	 */
	public void playerStand() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.STAND);
		StandAction action = new StandAction(gamePanel);
		action.processAction(event);
	}

	/**
	 * Calls the insurance action for the player
	 */
	public void insurance() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.INSURANCE);
		InsuranceAction action = new InsuranceAction(gamePanel);
		action.processAction(event);
	}

	/**
	 * Calls the double down action
	 */
	public void doubleDown() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.DOUBLE_DOWN);
		DoubleDownAction action = new DoubleDownAction(gamePanel);
		action.processAction(event);
	}

	/**
	 * Calls the split action
	 */
	public void split() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.SPLIT);
		SplitAction action = new SplitAction(gamePanel);
		action.processAction(event);
	}

	/**
	 * Calls the surrender action
	 */
	public void surrender() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.SURRENDER);
		SurrenderAction action = new SurrenderAction(gamePanel);
		action.processAction(event);
	}

	/**
	 * Calls the BetAction and passes in the bet amount
	 * @param amount the amount to bet
	 */
	public void betPlus(int amount) {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.BET);
		BetAction action = new BetAction(gamePanel, amount);
		action.processAction(event);
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void noInsurance() {
		GameEvent event = new GameEvent(gameState);
		event.setActionType(ActionType.INSURANCE);
		DeclineInsuranceAction action = new DeclineInsuranceAction(gamePanel);
		action.processAction(event);
	}
}
