package com.rodrigohf.apicompras.services.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsControllerAdvice {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiException> validationNotfoundException(RuntimeException ex , HttpServletRequest request){
		
		ApiException obj = new ApiException(LocalDateTime.now(),
				ex.getMessage(),
				HttpStatus.NOT_FOUND.value(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiException> dataIntegrity(DataIntegrityViolationException ex , HttpServletRequest request){
		
		ApiException obj = new ApiException(LocalDateTime.now(),
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj);
}
}
