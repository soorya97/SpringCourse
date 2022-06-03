package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//The repository must be an interface
//We must extend JpaRepository with Post as the entity and the primary key's type as Integer as ID is the PK
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}