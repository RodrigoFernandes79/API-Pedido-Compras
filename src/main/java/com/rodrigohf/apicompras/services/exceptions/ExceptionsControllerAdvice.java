package com.rodrigohf.apicompras.services.exceptions;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ApiException> dataIntegrity(DataIntegrityException ex , HttpServletRequest request){
		String fieldName = ex.getMessage();
		
		ApiException obj = new ApiException(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Integridade de Dados",
				fieldName,
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


		ApiException obj = new ApiException(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				erro.toString(),
				ex.getMessage(),
				request.getRequestURI());

	

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj);
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
