package com.revshop.authenticationservice.exception;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GloablExceptionHandler {
	
	
	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<String> handleEmailExistsException(EmailExistsException emailExistsException){
		return new ResponseEntity<String>(emailExistsException.getMessage(),HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> handleInvalidCredentalsException(InvalidCredentialsException invalidCredentialsException){
		return new ResponseEntity<String>(invalidCredentialsException.getMessage(), HttpStatus.UNAUTHORIZED); 
	}
	
	@ExceptionHandler(BlockedException.class)
	public ResponseEntity<String> handleBlockedException(BlockedException blockedException){
		return new ResponseEntity<String>(blockedException.getMessage(), HttpStatus.UNAUTHORIZED); 
	}
	
	@ExceptionHandler(SellerNotApprovedException.class)
	public ResponseEntity<String> handleNotApprovedSellersException(SellerNotApprovedException sellerNotApprovedException){
		return new ResponseEntity<String>(sellerNotApprovedException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
