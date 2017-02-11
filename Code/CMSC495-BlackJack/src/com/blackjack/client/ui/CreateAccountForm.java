package com.blackjack.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class CreateAccountForm extends FormPanel {

	private Label userNameLabel;
	private TextBox userNameTextBox;
	private Label emailLabel;
	private TextBox emailTextBox;
	private Label passwordLabel;
	private PasswordTextBox passwordTextBox;
	private Label confirmPasswordLabel;
	private PasswordTextBox confirmPasswordTextBox;
	private Button createAccountButton;
	private Button cancelButton;
	
	public CreateAccountForm() {
		this.setStylePrimaryName("modal");
		this.addStyleName("centered");
		FlowPanel panel = new FlowPanel();
		
		userNameLabel = new Label("Username:");
		userNameLabel.setStylePrimaryName("label");
		userNameLabel.addStyleDependentName("long");
		userNameLabel.addStyleDependentName("white");
		userNameLabel.addStyleDependentName("label-form");
		
		userNameTextBox = new TextBox();
		userNameTextBox.setStylePrimaryName("textbox");
		
		emailLabel = new Label("Email:");
		emailLabel.setStylePrimaryName("label");
		emailLabel.addStyleDependentName("long");
		emailLabel.addStyleDependentName("white");
		emailLabel.addStyleDependentName("label-form");

		
		emailTextBox = new TextBox();
		emailTextBox.setStylePrimaryName("textbox");
		
		passwordLabel = new Label("Password:");
		passwordLabel.setStylePrimaryName("label");
		passwordLabel.addStyleDependentName("long");
		passwordLabel.addStyleDependentName("white");
		passwordLabel.addStyleDependentName("label-form");

		
		passwordTextBox = new PasswordTextBox();
		passwordTextBox.setStylePrimaryName("textbox");
		
		confirmPasswordLabel = new Label("Confirm Password:");
		confirmPasswordLabel.setStylePrimaryName("label");
		confirmPasswordLabel.addStyleDependentName("long");
		confirmPasswordLabel.addStyleDependentName("white");
		confirmPasswordLabel.addStyleDependentName("label-form");

		
		confirmPasswordTextBox = new PasswordTextBox();
		confirmPasswordTextBox.setStylePrimaryName("textbox");
		
		createAccountButton = new Button("Create");
		createAccountButton.setStylePrimaryName("button");
		createAccountButton.addStyleDependentName("green");
		createAccountButton.setWidth("311px");
		
		cancelButton = new Button("Cancel");
		cancelButton.setStylePrimaryName("button");
		cancelButton.addStyleDependentName("red");
		cancelButton.setWidth("311px");
		
		panel.add(new ModalHeader("Create Account"));
		panel.add(userNameLabel);
		panel.add(userNameTextBox);
		panel.add(new HTML("<br/>"));
		panel.add(emailLabel);
		panel.add(emailTextBox);
		panel.add(new HTML("<br/>"));
		panel.add(passwordLabel);
		panel.add(passwordTextBox);
		panel.add(new HTML("<br/>"));
		panel.add(confirmPasswordLabel);
		panel.add(confirmPasswordTextBox);
		panel.add(new HTML("<br/>"));
		panel.add(createAccountButton);
		panel.add(new HTML("<br/>"));
		panel.add(cancelButton);
		this.add(panel);
	}
	
	public void clearValues() {
		userNameTextBox.setText("");
		emailTextBox.setText("");
		passwordTextBox.setText("");
		confirmPasswordTextBox.setText("");
	}
	
	public TextBox getUserNameTextBox() {
		return userNameTextBox;
	}
	
	public TextBox getEmailTextBox() {
		return emailTextBox;
	}
	
	public TextBox getPasswordTextBox() {
		return passwordTextBox;
	}
	
	public TextBox getConfirmPasswordTextBox() {
		return confirmPasswordTextBox;
	}
	
	public Button getCreateAccountButton() {
		return createAccountButton;
	}
	
	public Button getCancelButton() {
		return cancelButton;
	}
}
