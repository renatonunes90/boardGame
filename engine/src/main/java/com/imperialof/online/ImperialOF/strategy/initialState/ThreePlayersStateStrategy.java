package com.imperialof.online.ImperialOF.strategy.initialState;

import java.util.Arrays;
import java.util.List;

import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.model.Player;
import com.imperialof.online.ImperialOF.util.NationEnum;

public class ThreePlayersStateStrategy extends InitialStateStrategy {

	@Override
	protected boolean distributeNations(Match match) {
		boolean result = true;
		final List<Player> players = match.getPlayers();
		
		result &= bankService.receiveMoney(players.get(0), 24);
		result &= bankService.receiveMoney(players.get(1), 24);
		result &= bankService.receiveMoney(players.get(2), 24);
		
		Player playerOne = players.get(0);
		Player playerTwo = players.get(1);	
		Player playerThree = players.get(2);
		
		final NationEnum firstNation = getRandomNation(Arrays.asList(0,4,5,6));
		result &= setNationToPlayer(match, playerOne, firstNation);
		
		final NationEnum secondNation = getRandomNation(Arrays.asList(0,firstNation.getOrder(),4,5,6));	
		result &= setNationToPlayer(match, playerTwo, secondNation);
		
		final NationEnum thirdNation = getRandomNation(Arrays.asList(0,firstNation.getOrder(),secondNation.getOrder(),4,5,6));	
		result &= setNationToPlayer(match, playerThree, thirdNation);
		
		return result;
	}
	
	private boolean setNationToPlayer(final Match match, final Player player, final NationEnum nation) {
		boolean result = true;
		if(nation.equals(NationEnum.RUSSIA)) {
			result &= bankService.buyDue(player, match.getNation(NationEnum.RUSSIA), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.EUROPE), 2l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.BRAZIL), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.CHINA), 2l);
		} else if(nation.equals(NationEnum.CHINA)) {
			result &= bankService.buyDue(player, match.getNation(NationEnum.CHINA), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.USA), 2l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.EUROPE), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.INDIA), 2l);
		} else {
			result &= bankService.buyDue(player, match.getNation(NationEnum.INDIA), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.BRAZIL), 2l);	
			result &= bankService.buyDue(player, match.getNation(NationEnum.USA), 9l);
			result &= bankService.buyDue(player, match.getNation(NationEnum.RUSSIA), 2l);
		}
		return result;
	}
}
