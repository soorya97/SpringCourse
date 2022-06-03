package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

//Since this must be managed by Spring, we need to annotate it with @Component
@Component
public class UserServiceDAO {
	private static final List<User> users = new ArrayList<>();
	private static int userCount = 4;

	static {
		users.add(new User(1, "Adam 01", new Date()));
		users.add(new User(2, "Adam 02", new Date()));
		users.add(new User(3, "Adam 03", new Date()));
	}

	public List<User> findAll(){
		return users;
	}

	public User save(User user){
		if(user.getUserID() == null){
			user.setUserID(userCount);
			userCount++;
		}

		users.add(user);
		return user;
	}

	public User findOne(int userID){
		for(User eachUser : users){
			if(eachUser.getUserID() == userID){
				return eachUser;
			}
		}

		return null;
	}

	public User deleteByID(int userID){
		Iterator<User> usersIterator = users.iterator();
		while(usersIterator.hasNext()) {
			User eachUser = usersIterator.next();
			if (eachUser.getUserID() == userID) {
				usersIterator.remove();
				return eachUser;
			}
		}

		return null;
	}
}
