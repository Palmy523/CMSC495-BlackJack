package com.blackjack.client.entities;

public class Card {

	public static enum Set {ONE}
	public static enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES}
	public static enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
	
	private Set set;
	private Suit suit;
	private Rank rank;
	
	public Card(Set set, Suit suit, Rank type) {
		this.set = set;
		this.suit = suit;
		this.rank = type;
	}

	/**
	 * @return the set
	 */
	public Set getSet() {
		return set;
	}

	/**
	 * @return the suit
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * @return the type
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * @param set the set to set
	 */
	public void setSet(Set set) {
		this.set = set;
	}

	/**
	 * @param suit the suit to set
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Rank type) {
		this.rank = type;
	}	
	
	public String getSuitName() {
		switch(suit) {
			case HEARTS : return "Hearts";
			case SPADES : return "Spades";
			case CLUBS : return "Clubs";
			case DIAMONDS : return "Diamonds";
			default: return null;
		}
	}
	
	public String getRankName() {
		switch(rank) {
			case ACE : return "Ace";
			case TWO : return "Two";
			case THREE : return "Three";
			case FOUR : return "Four";
			case FIVE : return "Five";
			case SIX : return "Six";
			case SEVEN : return "Seven";
			case EIGHT : return "Eight";
			case NINE : return "Nine";
			case TEN : return "Ten";
			case JACK : return "Jack";
			case QUEEN : return "Queen";
			case KING : return "King";
			default : return "Back";
		}
	}
}
