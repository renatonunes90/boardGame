package com.imperialof.online.ImperialOF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imperialof.online.ImperialOF.dto.DataWrapper;
import com.imperialof.online.ImperialOF.exception.BadRequestException;
import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.service.MatchService;

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
	
	@PostMapping(value="/{matchId}/join")
	public ResponseEntity<DataWrapper<Long>> joinMatch(@PathVariable Long matchId, @RequestParam String playerName) throws BadRequestException {
		return ResponseEntity.ok(new DataWrapper<Long>(matchService.joinMatch(matchId, playerName)));
	}
}
