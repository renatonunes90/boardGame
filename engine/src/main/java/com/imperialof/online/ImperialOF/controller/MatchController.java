package com.imperialof.online.ImperialOF.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imperialof.online.ImperialOF.dto.ActionInfo;
import com.imperialof.online.ImperialOF.dto.DataWrapper;
import com.imperialof.online.ImperialOF.exception.BadRequestException;
import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.model.Player;
import com.imperialof.online.ImperialOF.service.MatchService;
import com.imperialof.online.ImperialOF.util.ActionTypeEnum;

@RestController
@RequestMapping("/match")
public class MatchController {

	@Autowired
	private MatchService matchService;
	
	@PutMapping(value="/")
	public ResponseEntity<DataWrapper<Long>> create() {
		return ResponseEntity.ok(new DataWrapper<Long>(matchService.createMatch()));
	}
	
	@GetMapping(value="/{matchId}")
	public ResponseEntity<DataWrapper<Match>> getMatch(@PathVariable Long matchId) throws BadRequestException {
		final Match match = matchService.getMatch(matchId);
		if(match == null) {
			throw new BadRequestException("Match not found.");
		}

		return ResponseEntity.ok(new DataWrapper<Match>(match));
	}

	@GetMapping(value="/{matchId}/player")
	public ResponseEntity<DataWrapper<List<Player>>> getPlayersMatch(@PathVariable Long matchId) throws BadRequestException {
		final Match match = matchService.getMatch(matchId);
		if(match == null) {
			throw new BadRequestException("Match not found.");
		}

		return ResponseEntity.ok(new DataWrapper<List<Player>>(match.getPlayers()));
	}
	
	@PostMapping(value="/{matchId}/join")
	public ResponseEntity<DataWrapper<Long>> joinMatch(@PathVariable Long matchId, @RequestParam String playerName) throws BadRequestException {
		return ResponseEntity.ok(new DataWrapper<Long>(matchService.joinMatch(matchId, playerName)));
	}
	
	@PostMapping(value="/{matchId}/start")
	public ResponseEntity<DataWrapper<Match>> startMatch(@PathVariable Long matchId) throws BadRequestException {
		return ResponseEntity.ok(new DataWrapper<Match>(matchService.startMatch(matchId)));
	}
	
	@PostMapping(value="/{matchId}/{action}")
	public ResponseEntity<DataWrapper<Boolean>> executeAction(@PathVariable Long matchId, @PathVariable String action,
			@RequestBody ActionInfo actionInfo) throws BadRequestException {
		return ResponseEntity.ok(new DataWrapper<Boolean>(matchService.executeAction(matchId, ActionTypeEnum.fromCode(action), actionInfo)));
	}
}
