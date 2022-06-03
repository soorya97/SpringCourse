package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//to make this bean as the entity, make it @Entity
@Entity
public class User {

	//For making this as primary key
	@Id
	//For auto generation of values
	@GeneratedValue
	private Integer userID;

	//This is validate that the size of name will be >= 2
	@Size(min=2, message = "Size of NAME must be >= 2")
	private String name;

	//This is validate that the birthDate is always in the past
	@Past(message = "BIRTH DATE must be in the past")
	private Date birthDate;

	//since this many post will be associated with a single user, it must be a OneToMany
	//slso, the mapping must be user - this is the variable set in the Post entity
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public User(Integer userID, String name, Date birthDate) {
		this.userID = userID;
		this.name = name;
		this.birthDate = birthDate;
	}

	public User() {

	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User{" +
				"userID=" + userID +
				", name='" + name + '\'' +
				", birthDate=" + birthDate +
				'}';
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
