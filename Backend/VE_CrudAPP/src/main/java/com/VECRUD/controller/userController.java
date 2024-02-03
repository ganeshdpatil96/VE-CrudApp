package com.VECRUD.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.VECRUD.Model.*;
import com.VECRUD.exception.UserNotFoundException;
import com.VECRUD.repository.*;


@RestController
@CrossOrigin("http://localhost:3000")
public class userController {
	

	@Autowired
	private userRepository userRepository;
	
	@PostMapping("/user")
	user newuser(@RequestBody user newuser) {
		return userRepository.save(newuser);
	}
	
	@GetMapping("/users")
	List<user> getAllUsers(){
		return userRepository.findAll();	
		
	}
	
	@GetMapping("/user/{id}")
	user getUserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}
	
	@PutMapping("/user/{id}")
	user updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return userRepository.findById(id)
				.map(user ->{
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(((com.VECRUD.Model.user) newUser).getEmail());
					return userRepository.save(user);
				}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "User with id "+id+" has been deleted success.";
	}
	
}
