package com.blackjack.client.ui;

import com.google.gwt.user.client.ui.FlowPanel;

public class UserMessageBox extends FlowPanel {

	public enum MessageType {ERROR, INFO, WARN}
	private String message;
	private MessageType type;
	
	public UserMessageBox(MessageType type, String message) {
		
	}
	
}
