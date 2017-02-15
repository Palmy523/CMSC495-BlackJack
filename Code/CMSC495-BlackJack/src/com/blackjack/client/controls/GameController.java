package com.blackjack.client.controls;

import com.blackjack.client.action.GameAction;
import com.blackjack.client.action.HitAction;
import com.blackjack.client.entities.GameState;
import com.blackjack.client.entities.Hand;
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
	}
	
	public void quitGame() {
		//TODO quit the game and return to the RoomSelectionScreen
	}
	
	public void deal() {
		//TODO initiate a deal
	}
	
	public boolean canDeal() {
		//TODO determine if game is in a state that allows a deal
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
		GameEvent event = new GameEvent(gameState);
		HitAction action = new HitAction(100, gamePanel);
		action.processAction(event);
	}
	
	public boolean canPlayerHit() {
		//TODO determine if the game is in a state that allows the player to hit
		return false;
	}
	
	public void dealerHit() {
		//TODO hit the dealer hand with the top card on the deck
	}
	
	public boolean canDealerHit() {
		//TODO determine if the game is in a state that allows the dealer to hit
		return false;
	}
	
	public void stand(Hand hand) {
		//TODO perform a stand action for the specified hand
	}
	
	public void playerStand() {
		//TODO cause a player hand to stand
	}
	
	public boolean canPlayerStand() {
		//TODO determine if the game is in a state that allows the player to Stand.
		return false;
	}
	
	public void dealerStand() {
		//TODO cause the dealer hand to stand
	}
	
	public boolean canDealerStand() {
		//TODO determine if the game is in a state that allows the dealer to stand
		return false;
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
		return false;
	}
	
	public void betPlus(int amount) {
		//TODO increase the bet by the specified amount
	}
	
	public void betMinus(int amount) {
		//TODO decrease the bet by the specified amount
	}
	
	public boolean canBet() {
		//TODO determine if the game is in a state that allows a bet
		return false;
	}
	
	public void dealerWin() {
		//TODO end the hand with a dealer win
	}
	
	public void playerWin() {
		//TODO end the hand with a player win
	}
	
	public boolean updateChipCount(String userID, int newAmount) {
		//TODO send the updated chip amount to the server to update the db and update the UI
		//with the return value
		return false;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	
	
	
	
	
	
	
	
	
	
}
