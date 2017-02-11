package com.blackjack.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class UserMessageBox extends SimplePanel {

	public enum MessageType {ERROR, INFO, WARN}
	private String message;
	private MessageType type;
	
	private Label label = new Label();
	private Button button = new Button();
	
	public UserMessageBox() {	
		FlowPanel panel = new FlowPanel();
		this.setStylePrimaryName("modal");
		this.addStyleName("centered");
		this.addStyleDependentName("messagebox");
		
		label.setStylePrimaryName("label");
		label.addStyleDependentName("centered");
		
		button.setText("Close");
		button.setStylePrimaryName("button");
		button.addStyleDependentName("red");
		
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setVisible(false);
			}
			
		});
		panel.add(label);
		panel.add(new HTML("<br />"));
		panel.add(button);
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
			label.addStyleDependentName("black");
			label.addStyleDependentName("centered");
			this.setStylePrimaryName("modal");
			this.addStyleDependentName("gray");
			break;
		}
	}
	
}
