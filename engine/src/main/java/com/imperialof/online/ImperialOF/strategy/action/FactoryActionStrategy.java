package com.imperialof.online.ImperialOF.strategy.action;

import com.imperialof.online.ImperialOF.dto.ActionInfo;
import com.imperialof.online.ImperialOF.model.Match;

public class FactoryActionStrategy extends ActionStrategy {

	@Override
	public boolean doAction(final Match match, final ActionInfo action) {
		return gameService.buildFactory(match, action.getTargetRegion(), false);
	}

}
