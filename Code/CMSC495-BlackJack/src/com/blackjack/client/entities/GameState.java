package com.blackjack.client.entities;

import com.blackjack.shared.entities.Room;
import com.blackjack.shared.entities.User;

public class GameState {

	public enum TurnState {PLAYER_TURN, DEALER_TURN, AWAITING_BET, AWAITING_DEAL, HAND_END}
	
	private static TurnState turn;
	private static Hand playerHand;
	private static Hand playerSplitHand;
	private static Hand dealerHand;
	private static Deck deck;
	private static int betAmount;
	private static int insuranceBetAmt;
	private static Room room;
	private static User user;
	private static boolean isDoubledDown;
	private static boolean isInsurance;
	private static boolean isSplit;
	private static boolean isSurrender;
	private static boolean isEasyPlay;
	private static boolean isHittingPrimary;
	private static boolean isHittingSplit;
	
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
		GameState.dealerHand = dealerHand;
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
		GameState.deck = deck;
	}

	/**
	 * @return the betAmount
	 */
	public static int getBetAmount() {
		return betAmount;
	}
	
	/**
	 * @return the insuranceBetAmt
	 * @return
	 */
	public static int getInsuranceBetAmt() {
		return insuranceBetAmt;
	}

	/**
	 * @param betAmount the betAmount to set
	 */
	public static void setBetAmount(int betAmount) {
		GameState.betAmount = betAmount;
	}
	
	/**
	 * 
	 * @param insuranceBet to insuranceBet to set
	 */
	public static void setInsuranceAmt(int insuranceBet) {
		GameState.insuranceBetAmt = insuranceBet;
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

	public static boolean isDoubledDown() {
		return isDoubledDown;
	}

	public static void setDoubledDown(boolean isDoubledDown) {
		GameState.isDoubledDown = isDoubledDown;
	}

	public static boolean isInsurance() {
		return isInsurance;
	}

	public static void setInsurance(boolean isInsurance) {
		GameState.isInsurance = isInsurance;
	}

	public static boolean isSplit() {
		return isSplit;
	}

	public static void setSplit(boolean isSplit) {
		GameState.isSplit = isSplit;
	}

	public static boolean isSurrender() {
		return isSurrender;
	}

	public static void setSurrender(boolean isSurrender) {
		GameState.isSurrender = isSurrender;
	}

	public static boolean isEasyPlay() {
		return isEasyPlay;
	}

	public static void setEasyPlay(boolean isEasyPlay) {
		GameState.isEasyPlay = isEasyPlay;
	}

	public static Hand getPlayerSplitHand() {
		return playerSplitHand;
	}

	public static void setPlayerSplitHand(Hand playerSplitHand) {
		GameState.playerSplitHand = playerSplitHand;
	}

	public static void setInsuranceBetAmt(int insuranceBetAmt) {
		GameState.insuranceBetAmt = insuranceBetAmt;
	}

	public static boolean isHittingPrimary() {
		return isHittingPrimary;
	}

	public static void setHittingPrimary(boolean isHittingPrimary) {
		GameState.isHittingPrimary = isHittingPrimary;
	}

	public static boolean isHittingSplit() {
		return isHittingSplit;
	}

	public static void setHittingSplit(boolean isHittingSplit) {
		GameState.isHittingSplit = isHittingSplit;
	}
}
