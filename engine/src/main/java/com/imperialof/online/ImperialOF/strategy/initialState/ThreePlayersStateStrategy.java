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
		
		players.forEach(p -> {
			bankService.receiveMoney(p, 24);
		});
		
		Player playerOne = players.get(0);
		Player playerTwo = players.get(1);	
		Player playerThree = players.get(2);
		
		final NationEnum firstNation = getRandomNation(Arrays.asList(0,4,5,6));
		result &= setNationsToPlayer(match, playerOne, firstNation);
		
		final NationEnum secondNation = getRandomNation(Arrays.asList(0,firstNation.getOrder(),4,5,6));	
		result &= setNationsToPlayer(match, playerTwo, secondNation);
		
		final NationEnum thirdNation = getRandomNation(Arrays.asList(0,firstNation.getOrder(),secondNation.getOrder(),4,5,6));	
		result &= setNationsToPlayer(match, playerThree, thirdNation);
		
		return result;
	}
	
	private boolean setNationsToPlayer(final Match match, final Player player, final NationEnum nation) {
		boolean result = true;
		if(nation.equals(NationEnum.RUSSIA)) {
			result &= setNationToPlayer(match, player, NationEnum.RUSSIA);
			result &= setNationToPlayer(match, player, NationEnum.BRAZIL);
		} else if(nation.equals(NationEnum.CHINA)) {
			result &= setNationToPlayer(match, player, NationEnum.CHINA);
			result &= setNationToPlayer(match, player, NationEnum.EUROPE);
		} else {
			result &= setNationToPlayer(match, player, NationEnum.INDIA);
			result &= setNationToPlayer(match, player, NationEnum.USA);
		}
		return result;
	}
}
