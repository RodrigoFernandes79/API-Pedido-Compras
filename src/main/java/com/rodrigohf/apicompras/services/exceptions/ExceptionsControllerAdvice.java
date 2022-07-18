package com.rodrigohf.apicompras.services.exceptions;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	//Tratamento da Exceçao Validation
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiException> argumentValid(MethodArgumentNotValidException ex,HttpServletRequest request){
		List<String> erro =new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((objetoErro)->{
			 
			 String errorMessage =  objetoErro.getDefaultMessage();
		
			 erro.add(errorMessage);
		});
			
		
		ApiException obj = new ApiException(LocalDateTime.now(),
				erro.toString(),
				HttpStatus.BAD_REQUEST.value(),
				request.getRequestURI());
		
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj);
}
	//Tratamento de exceção personalizada de não autorização de usuario(spring Security)
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity<ApiException> authorization(InternalAuthenticationServiceException ex , HttpServletRequest request){
		
		ApiException obj = new ApiException(LocalDateTime.now(),
				ex.getMessage(),
				HttpStatus.FORBIDDEN.value(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(obj);
}
}
