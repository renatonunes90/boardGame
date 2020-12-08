package com.imperialof.online.ImperialOF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imperialof.online.ImperialOF.dto.DataWrapper;
import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.service.MatchService;

@RestController
@RequestMapping("/match")
public class MatchController {

	@Autowired
	private MatchService matchService;
	
	@PutMapping(value="/")
	public ResponseEntity<DataWrapper<Match>> create() {
		return ResponseEntity.ok(new DataWrapper<Match>(matchService.createMatch()));
	}
}
