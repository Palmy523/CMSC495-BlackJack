package com.blackjack.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public class ConfirmEmailForm extends SimplePanel {

	private TextBox confirmKeyTextBox;
	private Button submitButton;
	private Button cancelButton;
	
	public ConfirmEmailForm() {
		FlowPanel content = new FlowPanel();
		
		this.setStylePrimaryName("modal");
		this.addStyleName("centered");
		this.addStyleDependentName("top");
		
		Label label = new Label("Confirmation Key:");
		label.setStylePrimaryName("label");
		label.addStyleDependentName("form");
		label.addStyleDependentName("white");
		
		confirmKeyTextBox = new TextBox();
		confirmKeyTextBox.setStylePrimaryName("textBox");
		
		submitButton = new Button("Submit");
		submitButton.setStylePrimaryName("button");
		submitButton.addStyleDependentName("left");
		submitButton.addStyleDependentName("red");
		
		cancelButton = new Button("Cancel");
		cancelButton.setStylePrimaryName("button");
		cancelButton.addStyleDependentName("right");
		cancelButton.addStyleDependentName("red");
		
		content.add(label);
		content.add(confirmKeyTextBox);
		content.add(new HTML("<br/>"));
		content.add(submitButton);
		content.add(cancelButton);
		
		this.add(content);
	}

	public TextBox getConfirmKeyTextBox() {
		return confirmKeyTextBox;
	}

	public void setConfirmKeyTextBox(TextBox confirmKeyTextBox) {
		this.confirmKeyTextBox = confirmKeyTextBox;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(Button submitButton) {
		this.submitButton = submitButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}
	
	
	
}
