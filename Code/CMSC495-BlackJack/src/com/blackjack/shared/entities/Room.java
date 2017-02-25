package com.blackjack.shared.entities;

import com.blackjack.shared.entities.Stakes.StakeLevel;
import com.google.gwt.core.shared.GWT;

/**
 * Entity that represents a game room.
 * 
 * @author Dave
 *
 */
public class Room {

	//The minimum bet the player can place
	private int minBet;
	
	//The maximum bet the player can place
	private int maxBet;
	
	//The chip limit that needs to have been reached to enter
	//this room
	private int chipLimit;
	
	//The stakes level for the room
	private Stakes stakes;
	
	private boolean enabled = true;
	
	/**
	 * Default Constructor
	 */
	public Room(){};
	
	public Room(int minBet, int maxBet, int chipLimit, Stakes stakes) {
		this.minBet = minBet;
		this.maxBet = maxBet;
		this.chipLimit = chipLimit;
		this.stakes = stakes;
	}

	/**
	 * @return the minBet
	 */
	public int getMinBet() {
		return minBet;
	}

	/**
	 * @param minBet the minBet to set
	 */
	public void setMinBet(int minBet) {
		this.minBet = minBet;
	}

	/**
	 * @return the maxBet
	 */
	public int getMaxBet() {
		return maxBet;
	}

	/**
	 * @param maxBet the maxBet to set
	 */
	public void setMaxBet(int maxBet) {
		this.maxBet = maxBet;
	}

	/**
	 * @return the chipLimit
	 */
	public int getChipLimit() {
		return chipLimit;
	}

	/**
	 * @param chipLimit the chipLimit to set
	 */
	public void setChipLimit(int chipLimit) {
		this.chipLimit = chipLimit;
	}

	/**
	 * @return the stakes
	 */
	public Stakes getStakes() {
		return stakes;
	}

	/**
	 * @param stakes the stakes to set
	 */
	public void setStakes(Stakes stakes) {
		this.stakes = stakes;
	}
	
	public static Room createLowStakesRoom() {
		Room room = new Room();
		Stakes stakes = new Stakes(StakeLevel.LOW);
		room.setChipLimit(1);
		room.setMaxBet(5);
		room.setMinBet(1);
		room.setStakes(stakes);
		return room;
	}
	
	public static Room createMediumStakesRoom() {
		Room room = new Room();
		Stakes stakes = new Stakes(StakeLevel.MEDIUM);
		room.setChipLimit(2500);
		room.setMaxBet(50);
		room.setMinBet(25);
		room.setStakes(stakes);
		return room;
	}
	
	public static Room createHighStakesRoom() {
		Room room = new Room();
		Stakes stakes = new Stakes(StakeLevel.HIGH);
		room.setChipLimit(25000);
		room.setMaxBet(2500);
		room.setMinBet(500);
		room.setStakes(stakes);
		return room;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
}
