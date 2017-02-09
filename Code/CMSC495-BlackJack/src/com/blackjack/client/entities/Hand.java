package com.blackjack.client.entities;

public class Hand {

	public enum HandType {DEALER, PLAYER}

	private static final int MAX_CARDS = 21;
	private Card[] cards;
	private int numCards = 0;
	
	public Hand() {
		cards = new Card[MAX_CARDS];
	}
	
	public void hit(Card card) {
		if(numCards == MAX_CARDS) {
			return;
		}
		cards[numCards] = card;
		numCards++;
	}
	
	public void clear() {
		cards = new Card[MAX_CARDS];
	}
}
