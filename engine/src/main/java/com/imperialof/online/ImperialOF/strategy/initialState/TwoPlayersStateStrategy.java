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
		
		result &= bankService.receiveMoney(players.get(0), 35);
		result &= bankService.receiveMoney(players.get(1), 35);
		
		Player playerOne = players.get(0);
		Player playerTwo = players.get(1);
		final NationEnum firstNation = getRandomNation(Arrays.asList(0,3,4,5,6));
		if(firstNation.equals(NationEnum.CHINA)) {
			playerOne = players.get(1);
			playerTwo = players.get(0);
		}
		
		result &= bankService.buyDue(playerOne, match.getNation(NationEnum.RUSSIA), 9l);
		result &= bankService.buyDue(playerOne, match.getNation(NationEnum.EUROPE), 2l);
		result &= bankService.buyDue(playerOne, match.getNation(NationEnum.USA), 9l);
		result &= bankService.buyDue(playerOne, match.getNation(NationEnum.RUSSIA), 2l);
		result &= bankService.buyDue(playerOne, match.getNation(NationEnum.INDIA), 9l);
		result &= bankService.buyDue(playerOne, match.getNation(NationEnum.BRAZIL), 2l);
		
		result &= bankService.buyDue(playerTwo, match.getNation(NationEnum.CHINA), 9l);
		result &= bankService.buyDue(playerTwo, match.getNation(NationEnum.USA), 2l);
		result &= bankService.buyDue(playerTwo, match.getNation(NationEnum.BRAZIL), 9l);
		result &= bankService.buyDue(playerTwo, match.getNation(NationEnum.CHINA), 2l);
		result &= bankService.buyDue(playerTwo, match.getNation(NationEnum.EUROPE), 9l);
		result &= bankService.buyDue(playerTwo, match.getNation(NationEnum.INDIA), 2l);
		
		return result;
	}
}
