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
	
	public static int getValue(Rank rank) {
		switch (rank)
		{
		case ACE:
			return 11;
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case TEN:
		case JACK:
		case QUEEN:
		case KING:
			return 10;

		default:
			return 0;
		}
	}
	
	/**
	 * Returns the value of concat card
	 * @return the value of the card
	 */
	public int getValue() {
		return getValue(rank);
	}
}
