package com.in28minutes.rest.webservices.restfulwebservices.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

	@GetMapping("v1/person")
	public PersonV1 personV1(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2(){
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//http://localhost:8080/person/param?verson=1
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1(){
		return new PersonV1("Bob Charlie");
	}
	
	//http:localhost:8080/person/param?verson=2
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramV2(){
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//no PostMan adicionar o headers
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1(){
		return new PersonV1("Bob Charlie");
	}
	
	//no PostMan adicionar o headers
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2(){
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//no header poe Accept e no value poe application/vnd.company.app-v1+json
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1(){
		return new PersonV1("Bob Charlie");
	}
	
	//no header poe Accept e no value poe application/vnd.company.app-v2+json
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2(){
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
}
