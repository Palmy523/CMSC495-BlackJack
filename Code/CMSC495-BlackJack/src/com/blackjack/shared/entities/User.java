package com.blackjack.shared.entities;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userID;
	private String username;
	private String email;
	private float bankAmount;
	private float maxChips;
	private boolean isEasyPlay;
	
	//TODO create the User Object based on database data
	public User() {}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getBankAmount() {
		return bankAmount;
	}

	public void setBankAmount(float bankAmount) {
		this.bankAmount = bankAmount;
	}

	public float getMaxChips() {
		return maxChips;
	}

	public void setMaxChips(float maxChips) {
		this.maxChips = maxChips;
	}

	public boolean isEasyPlay() {
		return isEasyPlay;
	}

	public void setEasyPlay(boolean isEasyPlay) {
		this.isEasyPlay = isEasyPlay;
	}
	
	
	
	
}
