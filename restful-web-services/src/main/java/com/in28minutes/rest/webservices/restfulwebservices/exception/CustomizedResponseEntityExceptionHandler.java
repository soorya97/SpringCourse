package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

//This exception hanlder must be applicable for all controllers and the resources across the project
//hence we use "@ControllerAdvice"
//Since this gives a response back, it must be a @RestController
//we must also extend ResponseEntityExceptionHandler
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	//Since this must handle exceptions and also for all kinds of exceptions, we must use @ExceptionHandler(Exception.class)
	//This is the method that we need to override - in order to cutomize the exception response
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));
		return (new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR));
	}

	//to handle only the UserNotFoundException
	//We are customizing the response status alone
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));
		return (new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND));
	}

	//to make sure that we return our custom message and the response code,
	//we need to override this method
	//we can pass in the message and the exception.getBindingResult().toString()
	//set the response as BAD_REQUEST
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", exception.getBindingResult().toString());
		return (new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST));
	}
}
