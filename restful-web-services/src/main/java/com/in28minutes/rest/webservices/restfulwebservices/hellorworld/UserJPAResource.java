package com.in28minutes.rest.webservices.restfulwebservices.hellorworld;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.UserDaoService;
import com.in28minutes.rest.webservices.restfulwebservices.user.Post;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserRepository;

@RestController
public class UserJPAResource {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	//http://localhost:8089/users
	@GetMapping(path="/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping(path="/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable Integer id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("id-"+ id);
		}
		
		Resource<User> resource = new Resource<User>(user.get());
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource; 
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()){
			throw new UserNotFoundException("id-"+ id);
		}
		
		return  user.get().getPost();
	}
	
	@DeleteMapping(path="/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id){
		userRepository.deleteById(id);
		
	}
	
}
