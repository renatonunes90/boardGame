package com.imperialof.online.ImperialOF.strategy.initialState;

import java.util.ArrayList;
import java.util.List;

import com.imperialof.online.ImperialOF.exception.BadRequestException;
import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.model.Player;
import com.imperialof.online.ImperialOF.util.NationEnum;

public class FourOrMorePlayersStateStrategy extends InitialStateStrategy {

	@Override
	protected boolean distributeNations(Match match) {
		boolean result = true;
		final List<Player> players = match.getPlayers();
		
		final List<Integer> alreadyAdded = new ArrayList<>();
		alreadyAdded.add(0);
		
		try
		{
			players.forEach(p -> {
				bankService.receiveMoney(p, 13);
				NationEnum randomNation = getRandomNation(alreadyAdded);
				if(!setNationToPlayer(match, p, randomNation)) {
					throw new BadRequestException("Error seting nation to player.");
				}
				alreadyAdded.add(randomNation.getOrder());
			});
		} catch( Exception e ) {
			result = false;
		}
		
		return result;
	}
	
	private boolean setNationToPlayer(final Match match, final Player player, final NationEnum nation) {
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
}
