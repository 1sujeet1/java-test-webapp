package com.example.javatest.controller.advice;



import java.util.NoSuchElementException;

import com.example.javatest.dto.responsedto.AppResponse;
import com.example.javatest.exceptions.ConflictException;
import com.example.javatest.exceptions.NotExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * DefaultExceptionHandler
 */
@RestControllerAdvice
public class RestAdvice {

    @ExceptionHandler(NotExistsException.class)
	public ResponseEntity<AppResponse> notFound(NotExistsException notExistsException) {

		return notFoundResponse(notExistsException.getMessage());
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<AppResponse> repostitoryException(NoSuchElementException noSuchElementException) {

		AppResponse response = new AppResponse();
		response.setDescription(noSuchElementException.getLocalizedMessage());
		response.setResponse(noSuchElementException.getCause());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
	}



    @ExceptionHandler(ConflictException.class)
	public ResponseEntity<AppResponse> conflict(ConflictException conflictException) {

		AppResponse response = new AppResponse();
		response.setDescription(conflictException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	

	private ResponseEntity<AppResponse> notFoundResponse(String description) {
		AppResponse response = new AppResponse();
		response.setDescription(description);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

    
}