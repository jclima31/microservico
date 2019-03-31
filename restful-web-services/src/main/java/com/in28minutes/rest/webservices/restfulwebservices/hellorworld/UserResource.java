package com.in28minutes.rest.webservices.restfulwebservices.hellorworld;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.UserDaoService;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	//http://localhost:8089/users
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public User retrieveUser(@PathVariable Integer id){
		User user = service.findOne(id);
		if(user == null){
			//Classe de exceção criada
			throw new UserNotFoundException("id-"+ id);
		}
		
		return user; 
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	//Tentara buscar o usuario, se encontrar deleta, se nao encontrar 
	//Adiciona uma excecao de usuario nao encontrado
	@DeleteMapping(path="/users/{id}")
	public Resource<User> deleteUser(@PathVariable Integer id){
		User user = service.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("id-"+ id);
		
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
			
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
}
