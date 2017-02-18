package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Hand.HandType;
import com.blackjack.client.ui.GameButton.GameButtonType;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class BlackJackGamePanel extends SimplePanel {

	private int betAmount;
	private FlowPanel content;
	private GameButton insuranceButton;
	private GameButton doubleDownButton;
	private GameButton hitButton;
	private GameButton standButton;
	private GameButton splitButton;
	private GameButton surrenderButton;
	private ChipButton chip1;
	private ChipButton chip5;
	private ChipButton chip25;
	private ChipButton chip50;
	private ChipButton chip100;
	private HandPanel playerHandPanel;
	private HandPanel dealerHandPanel;
	private Button dealButton;
	private Label betLabelValue;
	
	
	public BlackJackGamePanel() {
		this.setStylePrimaryName("modal");
		this.addStyleName("centered");
		this.addStyleDependentName("game");
		this.setHeight("100%");
		this.setWidth("100%");
		
		content = new FlowPanel();
		FlowPanel buttonPanel = new FlowPanel();
		buttonPanel.setStylePrimaryName("gameButtonPanel");
		
		insuranceButton = new GameButton(GameButton.GameButtonType.INSURANCE);
		doubleDownButton = new GameButton(GameButton.GameButtonType.DOUBLE_DOWN);
		hitButton = new GameButton(GameButton.GameButtonType.HIT);
		standButton = new GameButton(GameButton.GameButtonType.STAND);
		splitButton = new GameButton(GameButton.GameButtonType.SPLIT);
		surrenderButton = new GameButton(GameButton.GameButtonType.SURRENDER);
		dealButton = new GameButton(GameButtonType.DEAL);
		
		buttonPanel.add(surrenderButton);
		buttonPanel.add(insuranceButton);
		buttonPanel.add(doubleDownButton);
		buttonPanel.add(splitButton);
		buttonPanel.add(hitButton);
		buttonPanel.add(standButton);
		buttonPanel.add(dealButton);
		
		playerHandPanel = new HandPanel(HandType.PLAYER);
		dealerHandPanel = new HandPanel(HandType.DEALER);
		
		FlowPanel chipPanel = new FlowPanel();
		chipPanel.setStylePrimaryName("chipPanel");
		
		Label betLabel = new Label("Current Bet ");
		betLabel.setStylePrimaryName("label");
		betLabel.addStyleDependentName("goldFont");
		betLabel.addStyleDependentName("underline");
		
		betLabelValue = new Label("$0");
		betLabelValue.setStylePrimaryName("label");
		betLabelValue.addStyleDependentName("goldFont");
		
		chip1 = new ChipButton(ChipButton.ChipValue.ONE);
		chip5 = new ChipButton(ChipButton.ChipValue.FIVE);
		chip25 = new ChipButton(ChipButton.ChipValue.TWENTY_FIVE);
		chip50 = new ChipButton(ChipButton.ChipValue.FIFTY);
		chip100 = new ChipButton(ChipButton.ChipValue.ONE_HUNDERED);
		
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
	
	/**
	 * Causes the UI to update
	 */
	public void hitPlayerHand(Card card) {
		playerHandPanel.hit(card);
	}
	
	public void hitDealerHand(Card card) {
		dealerHandPanel.hit(card);
	}
	
	public void playerStand() {
		//TODO implement stand for HandPanel, HandUI, and Hand and call
		playerHandPanel.stand();
	}
	
	public void dealerStand() {
		//TODO implement stand for HandPanel, HandUI, and Hand and call
		dealerHandPanel.stand();
	}
	
	/**
	 * Deals a card to the player, will not allow more than 
	 * two cards 
	 * 
	 * @param card the card to deal to the player
	 */
	public void dealPlayerCard(Card card) {
		if (playerHandPanel.getNumberPrimaryCards() < 2) {
			this.hitPlayerHand(card);
		}
	}
	
	/**
	 * Deal a card to the dealer, will not allow more than two cards,
	 * if the card is the second card, will set the UI to display the 
	 * card face down.
	 * 
	 * @param card the card to deal to the dealer
	 */
	public void dealDealerCard(Card card) {
		int numCards = dealerHandPanel.getNumberPrimaryCards();
		if (numCards < 2) {
			if (numCards == 1) {
				dealerHandPanel.hitFaceDown(card);
			} else {
				dealerHandPanel.hit(card);
			}
		}
	}
	
	/**
	 * Updates the UI with the appropriate bet amount, this is not
	 * the amount to set, but the amount to decrease or increase the current
	 * bet by. Use negative values for bet decreasing.
	 *  
	 * @param amount the amount to increase or decrease the current bet amount by
	 */
	public void bet(int amount) {
		betLabelValue.setText("$" + amount);
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

	public FlowPanel getContent() {
		return content;
	}

	public void setContent(FlowPanel content) {
		this.content = content;
	}

	public GameButton getInsuranceButton() {
		return insuranceButton;
	}

	public void setInsuranceButton(GameButton insuranceButton) {
		this.insuranceButton = insuranceButton;
	}

	public GameButton getDoubleDownButton() {
		return doubleDownButton;
	}

	public void setDoubleDownButton(GameButton doubleDownButton) {
		this.doubleDownButton = doubleDownButton;
	}

	public GameButton getHitButton() {
		return hitButton;
	}

	public void setHitButton(GameButton hitButton) {
		this.hitButton = hitButton;
	}

	public GameButton getStandButton() {
		return standButton;
	}

	public void setStandButton(GameButton standButton) {
		this.standButton = standButton;
	}

	public GameButton getSplitButton() {
		return splitButton;
	}

	public void setSplitButton(GameButton splitButton) {
		this.splitButton = splitButton;
	}

	public GameButton getSurrenderButton() {
		return surrenderButton;
	}

	public void setSurrenderButton(GameButton surrenderButton) {
		this.surrenderButton = surrenderButton;
	}

	public ChipButton getChip1() {
		return chip1;
	}

	public void setChip1(ChipButton chip1) {
		this.chip1 = chip1;
	}

	public ChipButton getChip5() {
		return chip5;
	}

	public void setChip5(ChipButton chip5) {
		this.chip5 = chip5;
	}

	public ChipButton getChip25() {
		return chip25;
	}

	public void setChip25(ChipButton chip25) {
		this.chip25 = chip25;
	}

	public ChipButton getChip50() {
		return chip50;
	}

	public void setChip50(ChipButton chip50) {
		this.chip50 = chip50;
	}

	public ChipButton getChip100() {
		return chip100;
	}

	public void setChip100(ChipButton chip100) {
		this.chip100 = chip100;
	}

	public HandPanel getPlayerHandPanel() {
		return playerHandPanel;
	}

	public void setPlayerHandPanel(HandPanel playerHandPanel) {
		this.playerHandPanel = playerHandPanel;
	}

	public HandPanel getDealerHandPanel() {
		return dealerHandPanel;
	}

	public void setDealerHandPanel(HandPanel dealerHandPanel) {
		this.dealerHandPanel = dealerHandPanel;
	}

	public Button getDealButton() {
		return dealButton;
	}

	public void setDealButton(Button dealButton) {
		this.dealButton = dealButton;
	}
	
	/**
	 * Enables or disabled a button specified by the button type.
	 * @param button the button to disable/enable
	 * @param enabled if false will disable the button, if true will enable it
	 */
	public void enabled(GameButton.GameButtonType button, boolean enabled){
		switch(button){
			case INSURANCE:
				insuranceButton.setEnabled(enabled);
				break;
			case DOUBLE_DOWN:
				doubleDownButton.setEnabled(enabled);
				break;
			case HIT:				
				hitButton.setEnabled(enabled);
				break;
			case STAND:				
				standButton.setEnabled(enabled);
				break;
			case SPLIT:
				splitButton.setEnabled(enabled);
				break;
			case SURRENDER:
				surrenderButton.setEnabled(enabled);
				break;
			case DEAL:
				dealButton.setEnabled(enabled);
				break;			
		}
	}
	
	/**
	 * Disables all buttons for the user
	 */
	public void disableAllButtons(){
		insuranceButton.setEnabled(false);
		doubleDownButton.setEnabled(false);
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		splitButton.setEnabled(false);
		surrenderButton.setEnabled(false);
		dealButton.setEnabled(false);
		chip1.setEnabled(false);
		chip5.setEnabled(false);		
		chip25.setEnabled(false);
		chip50.setEnabled(false);
		chip100.setEnabled(false);		
	}

	
	
}
