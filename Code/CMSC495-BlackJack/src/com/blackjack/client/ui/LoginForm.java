package com.blackjack.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * UI used for user login.
 * 
 * @author Dave
 *
 */
public class LoginForm extends SimplePanel {

	private Label userNameLabel;
	private TextBox userNameTextBox;
	private Label passwordLabel;
	private PasswordTextBox passwordTextBox;
	private Button loginButton;
	private Button forgotPasswordButton;
	private Button createAccountButton;
	private HTML logo;
	
	/**
	 * UI for user login.
	 */
	public LoginForm() {
		
		this.setStylePrimaryName("modal");
		this.setStyleName("centered", true);
		FlowPanel wrapper = new FlowPanel();
		
		logo = new HTML("<div class='logo'></div>");
		
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
		wrapper.add(logo);
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
	
	public TextBox getUsernameTextBox() {
		return userNameTextBox;
	}
	
	public PasswordTextBox getPasswordTextBox() {
		return passwordTextBox;
	}
	
	public Button getLoginButton() {
		return loginButton;
	}
	
	public Button getForgotPasswordButton() {
		return forgotPasswordButton;
	}
	
	public Button getCreateAccountButton() {
		return createAccountButton;
	}
}
