package com.blackjack.client.entities;

import com.blackjack.shared.entities.Room;
import com.blackjack.shared.entities.User;

public class GameState {

	public enum TurnState {PLAYER_TURN, DEALER_TURN, AWAITING_BET, AWAITING_DEAL, HAND_END}
	
	private static TurnState turn;
	private static Hand playerHand;
	private static Hand dealerHand;
	private static Deck deck;
	private static int betAmount;
	private static Room room;
	private static User user;
	
	/**
	 * @return the turn
	 */
	public static TurnState getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public static void setTurn(TurnState turn) {
		GameState.turn = turn;
	}

	/**
	 * @return the playerHand
	 */
	public static Hand getPlayerHand() {
		return playerHand;
	}

	/**
	 * @param playerHand the playerHand to set
	 */
	public static void setPlayerHand(Hand playerHand) {
		GameState.playerHand = playerHand;
	}

	/**
	 * @return the dealerHand
	 */
	public static Hand getDealerHand() {
		return dealerHand;
	}

	/**
	 * @param dealerHand the dealerHand to set
	 */
	public static void setDealerHand(Hand dealerHand) {
		dealerHand = dealerHand;
	}

	/**
	 * @return the deck
	 */
	public static Deck getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public static void setDeck(Deck deck) {
		deck = deck;
	}

	/**
	 * @return the betAmount
	 */
	public static int getBetAmount() {
		return betAmount;
	}

	/**
	 * @param betAmount the betAmount to set
	 */
	public static void setBetAmount(int betAmount) {
		betAmount = betAmount;
	}

	public static Room getRoom() {
		return room;
	}

	public static void setRoom(Room room) {
		GameState.room = room;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		GameState.user = user;
	}
	
	
}
