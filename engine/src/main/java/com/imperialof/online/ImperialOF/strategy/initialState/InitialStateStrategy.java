package com.imperialof.online.ImperialOF.strategy.initialState;

import java.util.List;

import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.model.Player;
import com.imperialof.online.ImperialOF.service.BankService;
import com.imperialof.online.ImperialOF.service.GameService;
import com.imperialof.online.ImperialOF.util.NationEnum;

public abstract class InitialStateStrategy {

	protected BankService bankService;
	
	protected GameService gameService;

	public void setBankService(final BankService bankService) {
		this.bankService = bankService;
	}
	
	public void setGameService(final GameService gameService) {
		this.gameService = gameService;
	}

	public boolean startGame(final Match match) {
		if(match != null && distributeNations(match) && buildFactories(match) && distributeInvestorCard(match)) {
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
	 
	protected boolean setNationToPlayer(final Match match, final Player player, final NationEnum nation) {
		boolean result = true;
		if(nation.equals(NationEnum.RUSSIA)) {
			result &= bankService.buyDue(player, match.getNation(NationEnum.RUSSIA), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.EUROPE), 2l);
		} else if(nation.equals(NationEnum.CHINA)) {
			result &= bankService.buyDue(player, match.getNation(NationEnum.CHINA), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.USA), 2l);
		} else if(nation.equals(NationEnum.INDIA)) {
			result &= bankService.buyDue(player, match.getNation(NationEnum.INDIA), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.BRAZIL), 2l);	
		} else if(nation.equals(NationEnum.BRAZIL)) {
			result &= bankService.buyDue(player, match.getNation(NationEnum.BRAZIL), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.CHINA), 2l);
		} else if(nation.equals(NationEnum.USA)) {
			result &= bankService.buyDue(player, match.getNation(NationEnum.USA), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.RUSSIA), 2l);
		} else {
			result &= bankService.buyDue(player, match.getNation(NationEnum.EUROPE), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.INDIA), 2l);
		}
		return result;
	}
	
	private boolean buildFactories(final Match match) {
		Boolean result = gameService.buildFactory(match, "Brasilia", true);
		result &= gameService.buildFactory(match,"Rio de Janeiro", true);
		result &= gameService.buildFactory(match,"Chicago", true);
		result &= gameService.buildFactory(match,"New Orleans", true);
		result &= gameService.buildFactory(match,"Paris", true);
		result &= gameService.buildFactory(match,"London", true);
		result &= gameService.buildFactory(match,"Moscow", true);
		result &= gameService.buildFactory(match,"Vladivostok", true);
		result &= gameService.buildFactory(match,"Beijing", true);
		result &= gameService.buildFactory(match,"Shanghai", true);
		result &= gameService.buildFactory(match,"New Delhi", true);
		result &= gameService.buildFactory(match,"Mumbai", true);
		return result;
	}
	
	private boolean distributeInvestorCard(final Match match) {
		Player firstInvestor = null;
		Player ownerOfRussia = gameService.getOwnerOfNation(match, NationEnum.RUSSIA);
		Player ownerOfChina = gameService.getOwnerOfNation(match, NationEnum.CHINA);
		if ( ownerOfRussia != null ) {
			firstInvestor = gameService.getLeftPlayerOf(match, ownerOfRussia);
		} else if ( ownerOfChina != null ) {
			firstInvestor = gameService.getLeftPlayerOf(match, ownerOfChina);
		} 
		
		if(firstInvestor == null){
			return false;
		}
		
		firstInvestor.setIsInvestor(true);
		return true;
	}
}
