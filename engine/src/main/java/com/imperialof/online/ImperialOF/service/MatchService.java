package com.imperialof.online.ImperialOF.service;

import org.springframework.stereotype.Service;

import com.imperialof.online.ImperialOF.model.Match;

@Service
public class MatchService {

	public Match createMatch() {
		return new Match();
	}
	
}
