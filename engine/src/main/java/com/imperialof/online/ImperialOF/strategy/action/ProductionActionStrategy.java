package com.imperialof.online.ImperialOF.strategy.action;

import com.imperialof.online.ImperialOF.dto.ActionInfo;
import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.util.NationEnum;

public class ProductionActionStrategy extends ActionStrategy {

	@Override
	public boolean doAction(final Match match, final ActionInfo action) {
		return gameService.trainTroops(match, NationEnum.fromCode(action.getNationCode()));
	}

}
