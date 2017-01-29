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

public class LoginForm extends FormPanel {

	private Label userNameLabel;
	private TextBox userNameTextBox;
	private Label passwordLabel;
	private PasswordTextBox passwordTextBox;
	private Button loginButton;
	private Button forgotPasswordButton;
	private Button createAccountButton;
	
	/**
	 * UI for user login.
	 */
	public LoginForm() {
		
		this.setStylePrimaryName("modal");
		this.setStyleName("centered", true);
		FlowPanel wrapper = new FlowPanel();
		
		ModalHeader header = new ModalHeader("Login");
		userNameLabel = new Label("Username:");
		userNameLabel.setStylePrimaryName("label");
		userNameLabel.addStyleDependentName("medium");
		userNameLabel.addStyleDependentName("white");
		userNameLabel.addStyleDependentName("form");
		
		userNameTextBox = new TextBox();
		userNameTextBox.addStyleName("textbox");
				
		passwordLabel = new Label("Password:");
		passwordLabel.setStylePrimaryName("label");
		passwordLabel.addStyleDependentName("medium");
		passwordLabel.addStyleDependentName("white");
		passwordLabel.addStyleDependentName("form");

		
		passwordTextBox = new PasswordTextBox();
		passwordTextBox.addStyleName("textbox");
		
		loginButton = new Button("Login");
		loginButton.setWidth("135px");
		loginButton.setStyleName("button");
		loginButton.addStyleDependentName("left");
		loginButton.addStyleDependentName("green");
		loginButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				submit();
			}
			
		});
		
		forgotPasswordButton = new Button("Forgot Password?");
		forgotPasswordButton.setStyleName("button");
		forgotPasswordButton.addStyleDependentName("red");
		forgotPasswordButton.setWidth("135px");
		forgotPasswordButton.addStyleDependentName("right");
		
		createAccountButton = new Button("Create New Account");
		createAccountButton.setStyleName("button");
		createAccountButton.addStyleDependentName("blue");
		createAccountButton.setWidth("279px");
		
		wrapper.add(header);
		wrapper.add(userNameLabel);
		wrapper.add(userNameTextBox);
		wrapper.add(new HTML("<br/>"));
		wrapper.add(passwordLabel);
		wrapper.add(passwordTextBox);
		wrapper.add(new HTML("<br/>"));
		wrapper.add(loginButton);
		wrapper.add(forgotPasswordButton);
		wrapper.add(new HTML("<br/>"));
		wrapper.add(createAccountButton);
		this.add(wrapper);
	}
	
	public void AddLoginHandler() {
		
	}
	
	public Button getForgotPasswordButton() {
		return forgotPasswordButton;
	}
	
	public Button getCreateAccountButton() {
		return createAccountButton;
	}
}
