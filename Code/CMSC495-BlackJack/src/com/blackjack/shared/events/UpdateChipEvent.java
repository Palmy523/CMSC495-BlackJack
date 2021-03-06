package com.blackjack.shared.events;

import java.io.Serializable;

import com.blackjack.shared.handlers.UpdateChipHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UpdateChipEvent extends GwtEvent<UpdateChipHandler> implements Serializable{

	private boolean isSuccess;
	private float newAmount;
	
	public static Type<UpdateChipHandler> TYPE = new Type<UpdateChipHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UpdateChipHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateChipHandler handler) {
		handler.processUpdateChipEvent(this);
	}

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the newAmount
	 */
	public float getNewAmount() {
		return newAmount;
	}

	/**
	 * @param newAmount the newAmount to set
	 */
	public void setNewAmount(float newAmount) {
		this.newAmount = newAmount;
	}

	
}
