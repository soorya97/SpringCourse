package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class PersonV1 {
	private String personName;

	public PersonV1(String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
}
