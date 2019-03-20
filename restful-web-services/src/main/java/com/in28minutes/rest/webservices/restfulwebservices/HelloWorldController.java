package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//http://localhost:8089/hello-world
	@GetMapping(path="/hello-world")
	public String helloWorld(){
		
		return "Hello World";
	}
}
