package com.springboot.angular.panel.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

//	@ExceptionHandler(ObjectNotFountException.class)
//	public ResponseEntity<StandardError> objectNotFound(ObjectNotFountException e, HttpServletRequest request) {
//		
//		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
//	}

//	@ExceptionHandler(DataIntegrityException.class)
//	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
//		
//		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> dataIntegrity(MethodArgumentNotValidException e, HttpServletRequest request) {

		Integer status = HttpStatus.UNPROCESSABLE_ENTITY.value();
		String error = "Error de validação.";
		Long timestamp = System.currentTimeMillis();
		String path = request.getRequestURI().toString();
		
		ValidationError validationError = new ValidationError(status, error, timestamp, path);

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			validationError.addError(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
	}

//	@ExceptionHandler(AuthorizationException.class)
//	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
//		
//		StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
//		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
//	}

}
