package com.jbk.Controller;

import java.net.URI;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jbk.Exception.UserNotFoundException;
import com.jbk.Model.User;
import com.jbk.Service.UserDaoService;

@RestController
public class UserResourceController {

	@Autowired
	UserDaoService service;
	
	
	

	@GetMapping(path = "/user")
	public List<User> retriveUsers() {

		return service.findAll();
	}

	@GetMapping(path = "/user/{id}")
	public User retriveUser(@PathVariable int id) {

		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id" + id);
		}
    
		return user;

	}

	@DeleteMapping(value = "deletebyid/{id}")
	public User userDeleteById(@PathVariable int id) {

		User user = service.deleteById(id);

		if (user == null) {
			throw new UserNotFoundException("id" + id);
		}
		return user;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User saveUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
}
