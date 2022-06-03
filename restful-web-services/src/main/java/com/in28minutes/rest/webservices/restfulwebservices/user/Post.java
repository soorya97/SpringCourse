package com.in28minutes.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer ID;

	private String description;

	//To avoid dead lock, let the fetch type be LAZY and we must ignore this via JsonIgnore
	//else the user will try to fetch the post & the post will try to fetch the user
	//since this many post will be associated with a single user, it must be a ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Post(Integer ID, String description, User user) {
		this.ID = ID;
		this.description = description;
		this.user = user;
	}

	public Post() {

	}

	//To avoid dead lock, dont return user
	//else the user will try to fetch the post & the post will try to fetch the user
	@Override
	public String toString() {
		return "Post{" +
				"ID=" + ID +
				", description='" + description + '\'' +
				'}';
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
