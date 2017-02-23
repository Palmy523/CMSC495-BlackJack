package com.blackjack.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

public class ChipButton extends FlowPanel {

	public enum ChipValue {ONE, FIVE, TWENTY_FIVE, FIFTY, ONE_HUNDERED}
	
	private boolean isEnabled;
	private Button chipButton;
	private Button plusButton;
	private Button minusButton;
	
	public ChipButton(ChipValue value) {
		this.setStylePrimaryName("chipContainer");
		chipButton = new Button();
		chipButton.setStylePrimaryName("chip");
		switch(value) {
			case ONE :
				chipButton.setText("$1");
				chipButton.addStyleDependentName("1");
				break;
			case FIVE :
				chipButton.setText("$5");
				chipButton.addStyleDependentName("5");
				break;
			case TWENTY_FIVE :
				chipButton.setText("$25");
				chipButton.addStyleDependentName("25");
				break;
			case FIFTY :
				chipButton.setText("$50");
				chipButton.addStyleDependentName("50");
				break;
			case ONE_HUNDERED :
				chipButton.setText("$100");
				chipButton.addStyleDependentName("100");
				break;
		}
		
		plusButton = new Button();
		plusButton.setText("+");
		plusButton.setStylePrimaryName("bet");
		plusButton.addStyleName("bet-plus");
		plusButton.addStyleName("button");
		
		minusButton = new Button();
		minusButton.setText("-");
		minusButton.setStylePrimaryName("bet");
		minusButton.addStyleName("bet-minus");
		minusButton.addStyleName("button");

		
		this.add(plusButton);
		this.add(chipButton);
		this.add(minusButton);
		
		setEnabled(true);
	}
	
	public void setEnabled(boolean enabled) {
		chipButton.setEnabled(enabled);
		plusButton.setEnabled(enabled);
		minusButton.setEnabled(enabled);
		isEnabled = enabled;
		if (enabled) {
			chipButton.removeStyleDependentName("disabled");
			plusButton.removeStyleDependentName("disabled");
			minusButton.removeStyleDependentName("disabled");

		} else {
			chipButton.addStyleDependentName("disabled");
			plusButton.addStyleDependentName("disabled");
			minusButton.addStyleDependentName("disabled");
		}
	}
	
	public Button getChipButton() {
		return chipButton;
	}

	public void setChipButton(Button chipButton) {
		this.chipButton = chipButton;
	}

	public Button getPlusButton() {
		return plusButton;
	}

	public void setPlusButton(Button plusButton) {
		this.plusButton = plusButton;
	}

	public Button getMinusButton() {
		return minusButton;
	}

	public void setMinusButton(Button minusButton) {
		this.minusButton = minusButton;
	}

	public boolean isEnabled() {
		return isEnabled;
	}
}
