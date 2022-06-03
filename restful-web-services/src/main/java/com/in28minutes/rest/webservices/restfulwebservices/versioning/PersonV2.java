package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class PersonV2 {
	private Name personName;

	public PersonV2(Name personName) {
		this.personName = personName;
	}

	public Name getPersonName() {
		return personName;
	}

	public void setPersonName(Name personName) {
		this.personName = personName;
	}
}
