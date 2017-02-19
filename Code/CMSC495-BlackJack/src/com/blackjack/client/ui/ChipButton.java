package com.blackjack.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class ChipButton extends Button {

	public enum ChipValue {ONE, FIVE, TWENTY_FIVE, FIFTY, ONE_HUNDERED}
	
	private boolean isEnabled;
	
	public ChipButton(ChipValue value) {
		this.setStylePrimaryName("chip");
		setEnabled(true);
		switch(value) {
			case ONE :
				this.setText("$1");
				this.addStyleDependentName("1");
				break;
			case FIVE :
				this.setText("$5");
				this.addStyleDependentName("5");
				break;
			case TWENTY_FIVE :
				this.setText("$25");
				this.addStyleDependentName("25");
				break;
			case FIFTY :
				this.setText("$50");
				this.addStyleDependentName("50");
				break;
			case ONE_HUNDERED :
				this.setText("$100");
				this.addStyleDependentName("100");
				break;
		}
		
		this.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				removeStyleName("coin-flip");
				addStyleName("coin-flip");
			}
			
		});
	}
	
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		isEnabled = enabled;
		if (enabled) {
			this.removeStyleDependentName("disabled");
		} else {
			this.addStyleDependentName("disabled");
		}
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
}
