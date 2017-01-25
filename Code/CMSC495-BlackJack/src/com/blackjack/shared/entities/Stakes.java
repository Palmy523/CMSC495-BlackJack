package com.blackjack.shared.entities;

public class Stakes {

	public static String HIGH_STAKES_TEXT = "High Stakes";
	public static String MEDIUM_STAKES_TEXT = "Medium Stakes";
	public static String LOW_STAKES_TEXT = "Low Stakes";
	
	public static enum StakeLevel {LOW, MEDIUM, HIGH}
	private StakeLevel level;
	
	public Stakes(StakeLevel stakeLevel) {
		this.level = stakeLevel;
	}
	
	public String getStakesString() {
		switch(level) {
			case LOW : return LOW_STAKES_TEXT;
			case MEDIUM: return MEDIUM_STAKES_TEXT;
			case HIGH: return HIGH_STAKES_TEXT;
			default: return null;
		}
	}
	
	public StakeLevel getStakesLevel() {
		return level;
	}
}
