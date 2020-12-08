package com.imperialof.online.ImperialOF.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.imperialof.online.ImperialOF.dto.DataWrapper;
import com.imperialof.online.ImperialOF.dto.ErrorResponse;
import com.imperialof.online.ImperialOF.exception.BadRequestException;

@RestControllerAdvice
public class ResponseStatusAdvisor {
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<DataWrapper<ErrorResponse>> handleResponseStatus(ResponseStatusException ex) {
		final DataWrapper<ErrorResponse> response = new DataWrapper<>();
		response.setError(new ErrorResponse(ex.getReason()));
		return new ResponseEntity<DataWrapper<ErrorResponse>>(response , ex.getStatus());
	}	
	
}
