package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
	}

	//getters and setters are a must in a bean - only then the automatic convertors will work
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//auto generated toString
	@Override
	public String toString() {
		return "HelloWorldBean{" +
				"message='" + message + '\'' +
				'}';
	}
}