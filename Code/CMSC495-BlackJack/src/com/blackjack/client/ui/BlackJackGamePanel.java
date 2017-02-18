package com.blackjack.client.ui;

import com.blackjack.client.entities.Card;
import com.blackjack.client.entities.Hand.HandType;
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
	private ChipButton chip10;
	private ChipButton chip25;
	private ChipButton chip50;
	private ChipButton chip100;
	private HandPanel playerHandPanel;
	private HandPanel dealerHandPanel;
	private Button dealButton;
	
	
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
		
		dealButton = new Button("Deal");
		dealButton.setStylePrimaryName("button");
		dealButton.addStyleDependentName("deal");
		
		buttonPanel.add(surrenderButton);
		buttonPanel.add(insuranceButton);
		buttonPanel.add(doubleDownButton);
		buttonPanel.add(splitButton);
		buttonPanel.add(hitButton);
		buttonPanel.add(standButton);
		
		playerHandPanel = new HandPanel(HandType.PLAYER);
		dealerHandPanel = new HandPanel(HandType.DEALER);
		
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
		content.add(dealButton);
		
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
	
	public void dealPlayerCard(Card card) {
		//TODO may just be able to call hit? Maybe something else should happen with a deal?
	}
	
	public void dealDealerCard(Card card) {
		//TODO may just be able to call hit? Maybe something else should happen with a deal?
	}
	
	/**
	 * Updates the UI with the appropriate bet amount, this is not
	 * the amount to set, but the amount to decrease or increase the current
	 * bet by. Use negative values for bet decreasing.
	 *  
	 * @param amount the amount to increase or decrease the current bet amount by
	 */
	public void bet(int amount) {
		//TODO implement a current bet label and increase/decrease bet
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

	public ChipButton getChip10() {
		return chip10;
	}

	public void setChip10(ChipButton chip10) {
		this.chip10 = chip10;
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
	
	public void disableButton(GameButton.GameButtonType button){
		switch(button){
			case INSURANCE:
				insuranceButton.setEnabled(false);
				break;
			case DOUBLE_DOWN:
				doubleDownButton.setEnabled(false);
				break;
			case HIT:				
				hitButton.setEnabled(false);
				break;
			case STAND:				
				standButton.setEnabled(false);
				break;
			case SPLIT:
				splitButton.setEnabled(false);
				break;
			case SURRENDER:
				surrenderButton.setEnabled(false);
				break;
			case DEAL:
				dealButton.setEnabled(false);
				break;			
		}
	}
	
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
