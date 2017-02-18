package com.blackjack.client.controls;

import com.blackjack.client.action.BetAction;
import com.blackjack.client.action.DealAction;
import com.blackjack.client.action.GameAction.ActionType;
import com.blackjack.client.action.HitAction;
import com.blackjack.client.action.StandAction;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.GameState.TurnState;
import com.blackjack.client.entities.Hand.HandType;
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
	}
	
	/**
	 * Starts a game of 21!
	 * @param room
	 * @param isEasyPlay
	 */
	public void startGame() {
		dashboard.displayGamePanel();
		gamePanel.disableAllButtons();
		GameState.setTurn(TurnState.AWAITING_BET);
	}
	
	public void quitGame() {
		//TODO quit the game and return to the RoomSelectionScreen
	}
	
	public void deal() {		
		if(canDeal()){
			//Set the GameState.TurnState to PLAYER_TURN
			GameState.setTurn(TurnState.DEALER_TURN);
			//Create a GameEvent with the current GameState.
			GameEvent event = new GameEvent(gameState);		
			//Create a new DealAction
			DealAction action = new DealAction(100, gamePanel);
			//Invoke the processAction method of the DealAction
			action.processAction(event);
		}		
	}
	
	public boolean canDeal() {
		//Determine if game is in a state that allows a deal
		if(GameState.getTurn() == TurnState.AWAITING_DEAL)
			return true;
		return false;
	}
	
	public void hit(HandType type) {
		if (type == HandType.PLAYER) {
			playerHit();
		} else {
			dealerHit();
		}
	}
	
	public void playerHit() {
		if (canPlayerHit()) {
			GameEvent event = new GameEvent(gameState);
			HitAction action = new HitAction(100, gamePanel);
			action.processAction(event);
		}
	}
	
	public boolean canPlayerHit() {
		//TODO determine if the game is in a state that allows the player to hit
		if(GameState.getTurn() == TurnState.PLAYER_TURN){
			return true;
		}
		return false;
	}
	
	public void dealerHit() {
		if (canDealerHit()) {
			GameEvent event = new GameEvent(gameState);
			HitAction action = new HitAction(100, gamePanel);
			action.processAction(event);
		}
	}
	
	public boolean canDealerHit() {
		if(GameState.getTurn() == TurnState.DEALER_TURN){
			return true;
		}
		return false;
	}
	
	/*public void stand(Hand hand) {
		//TODO perform a stand action for the specified hand
	}*/
	public void stand(HandType type) {
		if(type == HandType.PLAYER){
			playerStand();
		}else{
			dealerStand();
		}
	}
	
	public void playerStand() {
		//TODO cause a player hand to stand
		if(canPlayerStand()){
			GameEvent event= new GameEvent(gameState);
			StandAction action = new StandAction(100, gamePanel);
			action.processAction(event);			
		}
	}
	
	public boolean canPlayerStand() {
		//TODO determine if the game is in a state that allows the player to Stand.
		if(gameState.getTurn() == TurnState.PLAYER_TURN){
			return true;
		}
		return false;
	}
	
	public void dealerStand() {
		if(canDealerStand()){
			GameEvent event= new GameEvent(gameState);
			StandAction action = new StandAction(100, gamePanel);
			action.processAction(event);			
		}
		//TODO cause the dealer hand to stand
	}
	
	public boolean canDealerStand() {
		return gameState.getTurn() == TurnState.DEALER_TURN;
	}
	
	public void insurance() {
		//TODO invokes insurance for the player
	}
	
	public boolean canInsurance() {
		//TODO determine if the game is in a state that allows for insurance
		return false;
	}
	
	public void doubleDown() {
		//TODO cause the player to Double Down
	}
	
	public boolean canDoubleDown() {
		//TODO determine if the game is in a state that allows the player to double down
		return false;
	}
	
	public void split() {
		//TODO splits the players hand
		
	}
	
	public boolean canSplit() {
		//TODO determine if the game is in a state that allows a split
		return false;
	}
	
	public void surrender() {
		//TODO causes the player to surrender
	}
	
	public boolean canSurrender() {
		//TODO determine if the game is in a state that allows a surrender
		if(GameState.getTurn() == TurnState.PLAYER_TURN){
			return true;
		}
		return false;
	}
	
	public void betPlus(int amount) {
		if (canBet()) {
			GameEvent event = new GameEvent(gameState);
			event.setActionType(ActionType.BET);
			BetAction action = new BetAction(gamePanel, amount);
			action.processAction(event);
		}
	}
	
	public void betMinus(int amount) {
		//TODO decrease the bet by the specified amount
	}
	
	public boolean canBet() {
		return GameState.getTurn() == TurnState.AWAITING_BET;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	
	
	
	
	
	
	
	
	
	
}
