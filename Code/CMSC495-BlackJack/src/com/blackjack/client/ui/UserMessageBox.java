package com.blackjack.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

public class UserMessageBox extends FocusPanel {

	public enum MessageType {ERROR, INFO, WARN}
	private String message;
	private MessageType type;
	
	private Label label = new Label();
	private Button button = new Button();
	private Button quitButton = new Button();	
	
	public UserMessageBox() {	
		FlowPanel panel = new FlowPanel();
		this.setStylePrimaryName("modal");
		this.addStyleName("centered");
		this.addStyleDependentName("messagebox");
		
		label.setStylePrimaryName("label");
		label.addStyleDependentName("centered");
		label.addStyleDependentName("white");
		
		button.setText("Close");
		button.setStylePrimaryName("button");
		button.addStyleDependentName("red");
		
		quitButton.setText("Quit");
		quitButton.setStylePrimaryName("button");
		quitButton.addStyleDependentName("red");
		quitButton.addStyleDependentName("right");
		quitButton.setWidth("50px");
		quitButton.setVisible(false);
		
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setVisible(false);
			}
			
		});
		
		this.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				int keyCode = event.getCharCode();
				if (keyCode == KeyCodes.KEY_ENTER) {
					button.click();
				}
			}
		});
		
		this.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				int keyCode = event.getNativeKeyCode();
				if (keyCode == KeyCodes.KEY_ESCAPE) {
					button.click();
				}
			}
		});
		
		panel.add(label);
		panel.add(new HTML("<br />"));
		panel.add(button);
		panel.add(quitButton);
		this.add(panel);
	}
	
	public void setMessage(String message) {
		label.setText(message);
	}
	
	public void setType(MessageType type) {
		switch(type) {
		case ERROR :
			label.removeStyleName("label");
			label.setStylePrimaryName("label");
			label.addStyleDependentName("white");
			label.addStyleDependentName("centered");
			this.setStylePrimaryName("modal");
			this.addStyleDependentName("red");
			break;
		case INFO :
			label.removeStyleName("label");
			label.setStylePrimaryName("label");
			label.addStyleDependentName("white");
			label.addStyleDependentName("centered");
			this.setStylePrimaryName("modal");
			this.addStyleDependentName("gray");
			break;
		case WARN:
			label.removeStyleName("label");
			label.setStylePrimaryName("label");
			label.addStyleDependentName("white");
			label.addStyleDependentName("centered");
			this.setStylePrimaryName("modal");
			this.addStyleDependentName("gray");
			quitButton.setVisible(true);
		}
	}
	
	public Button getOkButton() {
		return button;
	}
	
	public Button getQuitButton(){
		return quitButton;
	}
}
