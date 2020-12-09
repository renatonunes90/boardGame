package com.imperialof.online.ImperialOF.strategy.initialState;

import java.util.List;

import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.service.BankService;
import com.imperialof.online.ImperialOF.util.NationEnum;

public abstract class InitialStateStrategy {

	protected BankService bankService;

	public void setBankService(final BankService bankService) {
		this.bankService = bankService;
	}

	public boolean startGame(final Match match) {
		if(distributeNations(match) && buildFactories(match)) {
			match.startGame();
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract boolean distributeNations(final Match match);
	
	protected NationEnum getRandomNation(final List<Integer> toExclude) {
		Integer random = Long.valueOf(Math.round(Math.random()*6)).intValue();
		while(toExclude.contains(random)) {
			random = Long.valueOf(Math.round(Math.random()*6)).intValue();
		}
		return NationEnum.fromOrder(random);
	}
	 
	private boolean buildFactories(final Match match) {
		Boolean result = bankService.buildFactory(match, "Brasilia", true);
		result &= bankService.buildFactory(match,"Rio de Janeiro", true);
		result &= bankService.buildFactory(match,"Chicago", true);
		result &= bankService.buildFactory(match,"New Orleans", true);
		result &= bankService.buildFactory(match,"Paris", true);
		result &= bankService.buildFactory(match,"London", true);
		result &= bankService.buildFactory(match,"Moscow", true);
		result &= bankService.buildFactory(match,"Vladivostok", true);
		result &= bankService.buildFactory(match,"Beijing", true);
		result &= bankService.buildFactory(match,"Shanghai", true);
		result &= bankService.buildFactory(match,"New Delhi", true);
		result &= bankService.buildFactory(match,"Mumbai", true);
		return result;
	}
}
