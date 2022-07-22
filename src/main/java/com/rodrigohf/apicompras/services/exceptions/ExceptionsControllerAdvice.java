package com.rodrigohf.apicompras.services.exceptions;



import java.io.FileNotFoundException;
import java.nio.file.FileSystemNotFoundException;
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

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

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
}  //exceção personalizada de envio de imagens para o s3Bucket AmazonAWS
	@ExceptionHandler(FileSystemNotFoundException.class)
	public ResponseEntity<ApiException> file(FileNotFoundException ex , HttpServletRequest request){
		
		ApiException obj = new ApiException(LocalDateTime.now(),
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj);
}
	//exceção personalizada para o AmazonAWS
		@ExceptionHandler(AmazonServiceException.class)
		public ResponseEntity<ApiException> amazonService(AmazonServiceException ex , HttpServletRequest request){
			
			HttpStatus code = HttpStatus.valueOf(ex.getErrorCode());
			ApiException obj = new ApiException(LocalDateTime.now(),
					ex.getMessage(),
					code.value(),
					request.getRequestURI());
			
			return ResponseEntity.status(code.value()).body(obj);
	}
		@ExceptionHandler(AmazonClientException.class)
		public ResponseEntity<ApiException> amazonClient(AmazonClientException ex , HttpServletRequest request){
			
			
			ApiException obj = new ApiException(LocalDateTime.now(),
					ex.getMessage(),
					HttpStatus.BAD_REQUEST.value(),
					request.getRequestURI());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(obj);
	}
		@ExceptionHandler(AmazonS3Exception.class)
		public ResponseEntity<ApiException> amazonS3(AmazonS3Exception ex , HttpServletRequest request){
			
			
			ApiException obj = new ApiException(LocalDateTime.now(),
					ex.getMessage(),
					HttpStatus.BAD_REQUEST.value(),
					request.getRequestURI());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(obj);
	}
}
