package com.in28minutes.rest.webservices.restfulwebservices.version;

public class Name {

	private String name;
	private String lastName;
	
	public Name() {
	}
	
	public Name(String name, String lastName) {
		super();
		this.name = name;
		this.lastName = lastName;
	}
	public String getName() {
		return name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
