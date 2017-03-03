package com.blackjack.client.entities;

import com.blackjack.client.entities.Card.Rank;

/**
 * Entity class that represents the data behind a BlackJack hand
 * 
 * @author Dave
 *
 */
public class Hand {

	/**
	 * enum to determine if the hand is of dealer or player type.
	 */
	public enum HandType {DEALER, PLAYER}

	public static final int MAX_CARDS = 11;
	
	/**
	 * The cards in this hand
	 */
	private Card[] cards;
	
	/**
	 * The number of cards in this hand
	 */
	private int numCards = 0;
	
	/**
	 * Flag to determine if the value of this hand causes a bust.
	 */
	private boolean hasBusted = false;
	
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
		//TODO if (numCards == 2 && cards[1].getRank() == cards[0].getRank()) {
		if (numCards == 2 && cards[1].getValue() == cards[0].getValue()) {
			splitCard = cards[1];
			cards[1] = null;
			numCards--;
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
	
	/**
	 * Gets the current hand value for a hand, takes into account ace values.
	 * @return
	 */
	public int getHandValue() {
		int numberOfAces = 0;
		int total = 0;
		for (int i = 0; i < cards.length; i++)
		{
			
			if(cards[i] == null) {
				break;
			}
			
			if(cards[i].getRank() == Rank.ACE) {
				numberOfAces++;
			}
			
			total += Card.getValue(cards[i].getRank());
			
			if (total > 21 && numberOfAces > 0) {
				total -= 10;
				numberOfAces--;
			}
		}
		return total;
	}

	/**
	 * Determines if what would be the up card for a dealer, is an ace.
	 * @return
	 */
	public boolean showingAce() {
		if(cards[1].getRank() == Rank.ACE) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if this hand can be split.
	 * @return
	 */
	public boolean canSplit() {
		if(cards[0].getValue() == cards[1].getValue())
			return true;
		return false;
	}
	
	/**
	 * Busts this hand.
	 */
	public void busts(){
		hasBusted = true;
	}
	
	public boolean getBustStatus(){
		return hasBusted;
	}
	
	public int getNumCards(){
		return numCards;
	}
}
