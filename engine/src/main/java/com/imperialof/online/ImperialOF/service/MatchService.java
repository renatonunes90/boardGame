package com.imperialof.online.ImperialOF.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.imperialof.online.ImperialOF.exception.BadRequestException;
import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.model.Player;

@Service
public class MatchService {

	private List<Match> matches;
	
	public Long createMatch() {
		final Match newMatch = new Match();
		
		if ( matches == null ) {
			matches = new ArrayList<>();
		}
		matches.add(newMatch);
		
		return newMatch.getId();
	}
	
	public Long joinMatch(final Long matchId, final String playerName) throws BadRequestException {
		final Match match = getMatch(matchId);
		if(match == null) {
			throw new BadRequestException("Match not found.");
		}
		
		final Player player = match.addPlayer(playerName);
		if(player == null) {
			throw new BadRequestException(String.format("Unable to add player to game %s.", matchId));
		}
		return player.getId(); 
	}
	
	public Match getMatch(final Long matchId) {
		if ( matches != null ) {
			return matches.stream().filter(m -> matchId.equals(m.getId())).findFirst().orElse(null);
		} else { 
			return null;
		}
	}
}
