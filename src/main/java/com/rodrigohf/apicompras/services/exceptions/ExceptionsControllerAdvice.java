package com.rodrigohf.apicompras.services.exceptions;



import java.io.FileNotFoundException;
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
		
		ApiException obj = new ApiException(System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				"Não Encontrado",
				ex.getMessage(),
				
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiException> dataIntegrity(DataIntegrityViolationException ex , HttpServletRequest request){
		
		ApiException obj = new ApiException(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Integridade de Dados",
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj);
}
	//Tratamento da Exceçao Validation
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiException> argumentValid(MethodArgumentNotValidException ex,HttpServletRequest request){
		List<String> erro =new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((objetoErro)->{
			 
			 String errorMessage =  objetoErro.getDefaultMessage();
		
			 erro.add(errorMessage);
		});
			
		
		ApiException obj = new ApiException(System.currentTimeMillis(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de Validação",
				ex.getMessage(),
				request.getRequestURI());
		
		
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(obj);
}
	//Tratamento de exceção personalizada de não autorização de usuario(spring Security)
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity<ApiException> authorization(InternalAuthenticationServiceException ex , HttpServletRequest request){
		
		ApiException obj =new ApiException(System.currentTimeMillis(),
				HttpStatus.FORBIDDEN.value(),
				"Acesso Negado!",
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(obj);
}
	//Tratamento de exceção personalizada quando não encontrar imagens jpg ou png
		@ExceptionHandler(FileNotFoundException.class)
		public ResponseEntity<ApiException> file(FileNotFoundException ex , HttpServletRequest request){
			
			ApiException obj = new ApiException(System.currentTimeMillis(),
					HttpStatus.BAD_REQUEST.value(),
					"Erro de Arquivo",
					ex.getMessage(),
					request.getRequestURI());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj);
	}
}
