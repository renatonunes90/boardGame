package com.imperialof.online.ImperialOF.strategy.action;

import com.imperialof.online.ImperialOF.dto.ActionInfo;
import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.service.BankService;
import com.imperialof.online.ImperialOF.service.GameService;

public abstract class ActionStrategy {

	protected BankService bankService;
	
	protected GameService gameService;

	public void setBankService(final BankService bankService) {
		this.bankService = bankService;
	}
	
	public void setGameService(final GameService gameService) {
		this.gameService = gameService;
	}

	public abstract boolean doAction(final Match match, ActionInfo action);

}
