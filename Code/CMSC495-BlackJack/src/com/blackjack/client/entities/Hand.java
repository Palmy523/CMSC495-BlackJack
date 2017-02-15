package com.blackjack.client.entities;

/**
 * Entity class that represents the data behind a BlackJack hand
 * 
 * @author Dave
 *
 */
public class Hand {

	public enum HandType {DEALER, PLAYER}

	public static final int MAX_CARDS = 21;
	private Card[] cards;
	private int numCards = 0;
	
	/**
	 * A hand always starts empty.
	 */
	public Hand() {
		cards = new Card[MAX_CARDS];
	}
	
	/**
	 * Adds a card to this hand
	 * @param card the card to be added
	 */
	public void hit(Card card) {
		if (numCards >= MAX_CARDS) {
			return;
		}
		cards[numCards] = card;
		numCards++;
	}
	
	/**
	 * Removes and returns the second card in the hand if the 
	 * hand only has two cards and the cards are the same Rank.
	 * @return the second card in the hand, the one to split into
	 * the other hand
	 */
	public Card split() {
		Card splitCard = null;
		if (numCards == 2 && cards[1].getRank() == cards[0].getRank()) {
			splitCard = cards[1];
			cards[1] = null;
		}
		return splitCard;
	}
	
	/**
	 * Clears this hand of all cards
	 */
	public void clear() {
		cards = new Card[MAX_CARDS];
		numCards = 0;
	}
}
