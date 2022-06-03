package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {

	//we can auto wire it, so no need to create a new instance
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	//findAll - is the default for select *
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers(){
		return userRepository.findAll();
	}


	//HATEOAS will help us to return links along with the response - EntityModel<User> will be the return type
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id){
		//findById - is the default for select * from TABLE where COLUMN = Value
		Optional<User> foundUser = userRepository.findById(id);
		if(foundUser.isEmpty()){
			//if the user is not found, throw the custom exception with the needed message
			throw (new UserNotFoundException("id-" + id));
		}

		//We must create a enity model on the found user
		//create a WebMvcLinkBuilder, linkTo the medthod of this class and the method that we need
		//[These are static methods that are defined in WebMvcLinkBuilder]
		//model.add(linkToUsers.withRel("all-users")) - will be out custom key message in the response
		//foundUser.get() is the syntax
		EntityModel<User> model = EntityModel.of(foundUser.get());
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retriveAllUsers());
		model.add(linkToUsers.withRel("all-users"));

		return model;
	}

	//response code must be - CREATED
	//input - details of user
	//output - CREATED status & return the created URI
	//to validate the user, we can annotate it with @valid - done by java validation API
	//save is the default
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = userRepository.save(user);

		// /user/{id} must be the URI of the newly created user
		URI savedUserLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserID()).toUri();
		return ResponseEntity.created(savedUserLocation).build();
	}

	//deleteById is the default
	//it is of void return type, if the ID is not found - the exception is automatically handled by H2
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retriveAllPostsByAUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()){
			throw (new UserNotFoundException("id-" + id));
		}

		return user.get().getPosts();
	}

	@GetMapping("/jpa/users/{userID}/posts/{postID}")
	public Post retriveAPostsByAUser(@PathVariable int userID, @PathVariable int postID){
		Optional<Post> post = postRepository.findById(postID);
		if(post.isEmpty()){
			throw (new UserNotFoundException("id-" + postID));
		}

		return post.get();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForAUser(@PathVariable int id, @RequestBody Post post){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()){
			throw (new UserNotFoundException("id-" + id));
		}

		User selectedUser = user.get();
		post.setUser(selectedUser);
		postRepository.save(post);

		URI savedPostLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getID()).toUri();
		return ResponseEntity.created(savedPostLocation).build();
	}
}
