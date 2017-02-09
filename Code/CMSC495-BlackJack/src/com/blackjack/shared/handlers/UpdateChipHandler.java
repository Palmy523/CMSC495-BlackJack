package com.blackjack.shared.handlers;

import com.blackjack.shared.events.UpdateChipEvent;
import com.google.gwt.event.shared.EventHandler;

public interface UpdateChipHandler extends EventHandler {

	public void processUpdateChipEvent(UpdateChipEvent event);
}
