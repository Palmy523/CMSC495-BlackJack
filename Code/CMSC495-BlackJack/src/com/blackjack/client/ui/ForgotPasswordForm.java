package com.blackjack.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ForgotPasswordForm extends FormPanel {

	private Label emailLabel;
	private Button resetPasswordButton;
	private TextBox emailTextBox;
	
	public ForgotPasswordForm() {
		this.setStylePrimaryName("modal");
		this.setStyleName("centered", true);
		FlowPanel wrapper = new FlowPanel();
		
		emailLabel = new Label("Email:");
		emailLabel.setStylePrimaryName("label");
		emailLabel.addStyleDependentName("white");
		emailLabel.addStyleDependentName("form");
		
		emailTextBox = new TextBox();
		emailTextBox.addStyleName("textbox");
		
		resetPasswordButton = new Button("Reset Password");
		resetPasswordButton.setWidth("219px");
		resetPasswordButton.setStyleName("button");
		resetPasswordButton.addStyleDependentName("red");
		resetPasswordButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				submit();
			}
			
		});
		
		wrapper.add(new ModalHeader("Reset Password"));
		wrapper.add(emailLabel);
		wrapper.add(emailTextBox);
		wrapper.add(new HTML("<br/>"));
		wrapper.add(resetPasswordButton);
		this.add(wrapper);
	}
	
	public Button getResetPasswordButton() {
		return resetPasswordButton;
	}
	
	public TextBox getEmailTextBox() {
		return emailTextBox;
	}
}
