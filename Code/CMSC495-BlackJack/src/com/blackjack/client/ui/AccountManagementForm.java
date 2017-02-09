package com.blackjack.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public class AccountManagementForm extends SimplePanel {
	
	public AccountManagementForm() {
		this.setStylePrimaryName("modal");
		this.addStyleName("centered");
		this.addStyleDependentName("top");
		
		FlowPanel content = new FlowPanel();
		
		ModalHeader header = new ModalHeader("Account Management");
		
		Label resetPasswordLabel = new Label("Update Password");
		resetPasswordLabel.setWidth("100%");
		resetPasswordLabel.setStylePrimaryName("label");
		resetPasswordLabel.addStyleDependentName("underline");
		resetPasswordLabel.addStyleDependentName("goldFont");
		resetPasswordLabel.addStyleDependentName("centered");
		
		Label currentPasswordLabel = new Label("Current Password:");
		currentPasswordLabel.setStylePrimaryName("label");
		currentPasswordLabel.addStyleDependentName("white");
		currentPasswordLabel.addStyleDependentName("form");
		currentPasswordLabel.addStyleDependentName("long");

		
		PasswordTextBox currentPasswordTextBox = new PasswordTextBox();
		currentPasswordTextBox.setStylePrimaryName("textbox");
		
		
		Label newPasswordLabel = new Label("New Password:");
		newPasswordLabel.setStylePrimaryName("label");
		newPasswordLabel.addStyleDependentName("white");
		newPasswordLabel.addStyleDependentName("form");
		newPasswordLabel.addStyleDependentName("long");

		
		PasswordTextBox newPasswordTextBox = new PasswordTextBox();
		newPasswordTextBox.setStylePrimaryName("textbox");
		
		Label confirmPasswordLabel = new Label("Confirm Password:");
		confirmPasswordLabel.setStylePrimaryName("label");
		confirmPasswordLabel.addStyleDependentName("white");
		confirmPasswordLabel.addStyleDependentName("form");
		confirmPasswordLabel.addStyleDependentName("long");
		
		PasswordTextBox confirmPasswordTextBox = new PasswordTextBox();
		confirmPasswordTextBox.setStylePrimaryName("textbox");
		
		Button resetButton = new Button("Update Password");
		resetButton.setWidth("321px");
		resetButton.setStylePrimaryName("button");
		resetButton.addStyleDependentName("red");
		
		Label updateEmailLabel = new Label("Update Email");
		updateEmailLabel.setWidth("100%");
		updateEmailLabel.setStylePrimaryName("label");
		updateEmailLabel.addStyleDependentName("underline");
		updateEmailLabel.addStyleDependentName("goldFont");
		updateEmailLabel.addStyleDependentName("centered");
		
		Label newEmailAddressLabel = new Label("New Email: ");
		newEmailAddressLabel.setStylePrimaryName("label");
		newEmailAddressLabel.addStyleDependentName("white");
		newEmailAddressLabel.addStyleDependentName("form");
		newEmailAddressLabel.addStyleDependentName("long");
		
		TextBox newEmailTextBox = new TextBox();
		newEmailTextBox.setStylePrimaryName("textbox");
		
		Button updateEmailButton = new Button("Update Email");
		updateEmailButton.setWidth("321px");
		updateEmailButton.setStylePrimaryName("button");
		updateEmailButton.addStyleDependentName("red");
		
		content.add(header);
		content.add(resetPasswordLabel);
		content.add(new HTML("<br/>"));
		content.add(currentPasswordLabel);
		content.add(currentPasswordTextBox);
		content.add(new HTML("<br/>"));
		content.add(newPasswordLabel);
		content.add(newPasswordTextBox);
		content.add(new HTML("<br/>"));
		content.add(confirmPasswordLabel);
		content.add(confirmPasswordTextBox);
		content.add(new HTML("<br/>"));
		content.add(resetButton);
		content.add(new HTML("<br/>"));
		content.add(updateEmailLabel);
		content.add(new HTML("<br/>"));
		content.add(newEmailAddressLabel);
		content.add(newEmailTextBox);
		content.add(new HTML("<br/>"));
		content.add(updateEmailButton);
		
		
		
		
		this.add(content);
	}
	

}
