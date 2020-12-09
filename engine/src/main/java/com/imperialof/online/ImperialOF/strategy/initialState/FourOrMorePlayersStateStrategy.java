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
}
