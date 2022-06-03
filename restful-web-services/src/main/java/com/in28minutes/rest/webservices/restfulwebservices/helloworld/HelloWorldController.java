package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//First thing to do, Say to the Spring that this is a Controller
@RestController
public class HelloWorldController {
	//Spring will automatically map the message source with app the available messages*.properties files
	//messages.properties -> Default + English Locale
	//messages_fr.properties -> French Locale
	@Autowired
	private MessageSource messageSource;

	//GET method
	//URI - /hello-world
	//method that returns "Hello World"
	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld(){
		return "Hello World";
	}

	//bean that returns "Hello World"
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean(){
		return (new HelloWorldBean("Hello World"));
	}

	///hello-world/path-variable/testVariable
	//testVariable === {name}
	//if we need to read the path variable, pass it as a argument with "@PathVariable" annotation"
	//we can append or use string format -> String.format("string %s", variable);
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
		return (new HelloWorldBean(String.format("Hello World %s", name)));
	}

	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(){
		//en = Hellow World
		//fr = Bonjour

		//use messageSource.getMessage,
		//give these params
		//  1. key
		//  2. args if any, else null
		//  3. Locale of the browser or the client - [will be en or fr etc.]
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}