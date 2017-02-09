package com.blackjack.client.ui;

import com.blackjack.client.entities.Hand.HandType;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class BlackJackGamePanel extends SimplePanel {

	private FlowPanel content;
	private GameButton insuranceButton;
	private GameButton doubleDownButton;
	private GameButton hitButton;
	private GameButton standButton;
	private GameButton splitButton;
	private GameButton surrenderButton;
	private ChipButton chip1;
	private ChipButton chip5;
	private ChipButton chip10;
	private ChipButton chip25;
	private ChipButton chip50;
	private ChipButton chip100;
	
	
	public BlackJackGamePanel() {
		this.setStylePrimaryName("modal");
		this.addStyleName("centered");
		this.addStyleDependentName("game");
		this.setHeight("100%");
		this.setWidth("100%");
		
		content = new FlowPanel();
		FlowPanel buttonPanel = new FlowPanel();
		buttonPanel.setStylePrimaryName("gameButtonPanel");
		
		GameButton insuranceButton = new GameButton(GameButton.GameButtonType.INSURANCE);
		GameButton doubleDownButton = new GameButton(GameButton.GameButtonType.DOUBLE_DOWN);
		GameButton hitButton = new GameButton(GameButton.GameButtonType.HIT);
		GameButton standButton = new GameButton(GameButton.GameButtonType.STAND);
		GameButton splitButton = new GameButton(GameButton.GameButtonType.SPLIT);
		GameButton surrenderButton = new GameButton(GameButton.GameButtonType.SURRENDER);
		
		buttonPanel.add(surrenderButton);
		buttonPanel.add(insuranceButton);
		buttonPanel.add(doubleDownButton);
		buttonPanel.add(splitButton);
		buttonPanel.add(hitButton);
		buttonPanel.add(standButton);
		
		FlowPanel playerHandPanel = new HandPanel(HandType.PLAYER);
		FlowPanel dealerHandPanel = new HandPanel(HandType.DEALER);
		
		FlowPanel chipPanel = new FlowPanel();
		chipPanel.setStylePrimaryName("chipPanel");
		
		Label betLabel = new Label("Current Bet ");
		betLabel.setStylePrimaryName("label");
		betLabel.addStyleDependentName("goldFont");
		betLabel.addStyleDependentName("underline");
		
		Label betLabelValue = new Label("$0");
		betLabelValue.setStylePrimaryName("label");
		betLabelValue.addStyleDependentName("goldFont");
		
		ChipButton chip1 = new ChipButton(ChipButton.ChipValue.ONE);
		ChipButton chip5 = new ChipButton(ChipButton.ChipValue.FIVE);
		ChipButton chip25 = new ChipButton(ChipButton.ChipValue.TWENTY_FIVE);
		ChipButton chip50 = new ChipButton(ChipButton.ChipValue.FIFTY);
		ChipButton chip100 = new ChipButton(ChipButton.ChipValue.ONE_HUNDERED);
		
		chipPanel.add(betLabel);
		chipPanel.add(new HTML("<br/>"));
		chipPanel.add(betLabelValue);
		chipPanel.add(new HTML("<br/>"));
		chipPanel.add(chip1);
		chipPanel.add(chip5);
		chipPanel.add(chip25);
		chipPanel.add(chip50);
		chipPanel.add(chip100);
		
		content.add(buttonPanel);
		content.add(playerHandPanel);
		content.add(dealerHandPanel);
		content.add(chipPanel);
		
		this.add(content);
	}
}
