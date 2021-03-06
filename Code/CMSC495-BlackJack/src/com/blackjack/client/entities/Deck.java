package com.blackjack.client.entities;

import com.blackjack.client.entities.Card.Rank;
import com.blackjack.client.entities.Card.Set;
import com.blackjack.client.entities.Card.Suit;
import com.google.gwt.user.client.Random;

/**
 * Represents a deck of cards. A deck can consist of multiple decks, each deck
 * that makes up this deck will be comprised of a standard deck of cards with 
 * jokers excluded.
 * 
 * @author Dave
 *
 */
public class Deck {

	private Card[] cards;
	private Set set;
	private int numberOfDecks;
	private int numCards;
	
	/**
	 * 
	 * @param set the set of cards that makes up this deck
	 * @param numberOfDecks the number of standard 52 card decks that make up this deck
	 * @param autoShuffle determines if the deck should be shuffled on creation.
	 */
	public Deck(Set set, int numberOfDecks, boolean autoShuffle) {
		this.numberOfDecks = numberOfDecks;
		this.set = set;
		cards = new Card[52 * numberOfDecks];
		numCards = cards.length;
		int cardIndex = 0;
		for (int i = 0; i < numberOfDecks; i++) {
			for (Suit suit : Card.Suit.values()){
				for (Rank rank : Card.Rank.values()) {
					cards[cardIndex] = new Card(set, suit, rank);
					cardIndex++;
				}
			}
		}
		
		if (autoShuffle) {
			shuffleDeck();
		}
	}
	
	/**
	 * Draws the top card in this deck.
	 * @return the top card in the deck.
	 */
	public Card draw() {
		if (numCards == 0) {
			return null;
		}
		Card card = cards[numCards - 1];
		numCards--;
		return card;
	}
	
	/**
	 * Shuffle the cards in this deck;
	 */
	public void shuffleDeck() {
		for (int i = numCards - 1; i > 0; i--) {
			int index = Random.nextInt(i + 1);
			Card a = cards[index];
			cards[index] = cards[i];
			cards[i] = a;
		}
	}
	
	/**
	 * @return a percentage of cards used in the deck.
	 */
	public float getCardUsage() {
		return numCards / cards.length;
	}
	
	/**
	 * @return the set
	 */
	public Set getSet() {
		return set;
	}

	/**
	 * @param set the set to set
	 */
	public void setSet(Set set) {
		this.set = set;
	}

	/**
	 * @return the numberOfDecks
	 */
	public int getNumberOfDecks() {
		return numberOfDecks;
	}

	/**
	 * @param numberOfDecks the numberOfDecks to set
	 */
	public void setNumberOfDecks(int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
	}

	public int remainingCards() {
		return numCards;
	}
}
