package com.blackjack.client.ui;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Button;

/**
 * UI button that represents a button used in the game of 21!
 * @author Dave
 *
 */
public class GameButton extends Button {

	public enum GameButtonType {HIT, STAND, DOUBLE_DOWN, INSURANCE, SURRENDER, SPLIT, DEAL}
	
	private boolean isEnabled;
	
	public GameButton(GameButtonType type) {
		this.setStylePrimaryName("button-game");
		this.setWidth("120px");
		setEnabled(true);
		this.addMouseDownHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				if (isEnabled) {
					addStyleDependentName("depressed");
				}
			}
		});
		
		this.addMouseUpHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				if (isEnabled) {
					removeStyleDependentName("depressed");
				}
			}
			
		});
		
		switch(type) {
			case HIT :
				this.addStyleDependentName("hit");
				this.setText("HIT");
				break;
			case STAND :
				this.addStyleDependentName("stand");
				this.setText("STAND");
				break;
			case DOUBLE_DOWN :
				this.addStyleDependentName("doubleDown");
				this.setText("DOUBLE");
				break;
			case INSURANCE :
				this.addStyleDependentName("insurance");
				this.setText("INSURANCE");
				break;
			case SURRENDER :
				this.addStyleDependentName("surrender");
				this.setText("SURRENDER");
				break;
			case SPLIT : 
				this.addStyleDependentName("split");
				this.setText("SPLIT");
				break;
			case DEAL : 
				this.addStyleDependentName("deal");
				this.setText("DEAL");
		}
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
