package com.imperialof.online.ImperialOF.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imperialof.online.ImperialOF.dto.DataWrapper;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping(value="/")
	public ResponseEntity<DataWrapper<String>> test() {
		return ResponseEntity.ok(new DataWrapper<String>("Im running!"));
	}
}
