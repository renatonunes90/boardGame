package com.imperialof.online.ImperialOF.strategy.initialState;

import java.util.Arrays;
import java.util.List;

import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.model.Player;
import com.imperialof.online.ImperialOF.util.NationEnum;

public class TwoPlayersStateStrategy extends InitialStateStrategy {
	
	@Override
	protected boolean distributeNations(Match match) {
		boolean result = true;
		final List<Player> players = match.getPlayers();
		
		players.forEach(p -> {
			bankService.receiveMoney(p, 35);
		});
		
		Player playerOne = players.get(0);
		Player playerTwo = players.get(1);	
		
		final NationEnum firstNation = getRandomNation(Arrays.asList(0,3,4,5,6));
		result &= setNationsToPlayer(match, playerOne, firstNation);
		
		final NationEnum secondNation = getRandomNation(Arrays.asList(0,firstNation.getOrder(),3,4,5,6));	
		result &= setNationsToPlayer(match, playerTwo, secondNation);
		
		return result;
	}
	
	private boolean setNationsToPlayer(final Match match, final Player player, final NationEnum nation) {
		boolean result = true;
		if(nation.equals(NationEnum.RUSSIA)) {
			result &= setNationToPlayer(match, player, NationEnum.RUSSIA);
			result &= setNationToPlayer(match, player, NationEnum.USA);
			result &= setNationToPlayer(match, player, NationEnum.INDIA);
		} else {
			result &= setNationToPlayer(match, player, NationEnum.CHINA);
			result &= setNationToPlayer(match, player, NationEnum.BRAZIL);
			result &= setNationToPlayer(match, player, NationEnum.EUROPE);
		}
		return result;
	}
}
