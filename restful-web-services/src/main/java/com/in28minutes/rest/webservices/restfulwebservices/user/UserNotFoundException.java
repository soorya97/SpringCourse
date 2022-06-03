package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//to make sure that 404 is thrown instead of 500, use @ResponseStatus(HttpStatus.NOT_FOUND)
//since this is a exception, extend the RuntimeException
//overwrite the UserNotFoundException(String msg) constructor - this is done - since we have a custom msg to show
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
