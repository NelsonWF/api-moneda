package com.api.nawf.infrastructure.handlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.nawf.infrastructure.exceptions.ApiException;

@ControllerAdvice
public class GlobalExeptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ExceptionResponse> apiException(ApiException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(ex.getInternalCode());
		response.setErrorMessage(ex.getMessage());
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionResponse> runtimeException(RuntimeException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("400");
		response.setErrorMessage("No fue posible tramitar la petición");
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> internalServerError(Exception ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("500");
		response.setErrorMessage("No fue posible tramitar la petición");
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
