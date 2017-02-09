package com.blackjack.client.entities;

public class GameState {

	public enum TurnState {PLAYER_TURN, DEALER_TURN, AWAITING_BET, AWAITING_DEAL}
	
	private TurnState turn;
	private Hand playerHand;
	private Hand dealerHand;
	private Deck deck;
	private int betAmount;
	
	public GameState() {}

	/**
	 * @return the turn
	 */
	public TurnState getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(TurnState turn) {
		this.turn = turn;
	}

	/**
	 * @return the playerHand
	 */
	public Hand getPlayerHand() {
		return playerHand;
	}

	/**
	 * @param playerHand the playerHand to set
	 */
	public void setPlayerHand(Hand playerHand) {
		this.playerHand = playerHand;
	}

	/**
	 * @return the dealerHand
	 */
	public Hand getDealerHand() {
		return dealerHand;
	}

	/**
	 * @param dealerHand the dealerHand to set
	 */
	public void setDealerHand(Hand dealerHand) {
		this.dealerHand = dealerHand;
	}

	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	/**
	 * @return the betAmount
	 */
	public int getBetAmount() {
		return betAmount;
	}

	/**
	 * @param betAmount the betAmount to set
	 */
	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}
	
	
	
}
