package com.lifeline.grocerystore.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lifeline.grocerystore.exception.UserNotFoundException;
import com.lifeline.grocerystore.model.User;
import com.lifeline.grocerystore.repository.UserRepository;



@RestController

@CrossOrigin("https://grocery-store-management-react.vercel.app/") //CONNECT FRONTEND-BACKEND

public class UserController 
{
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) 
	{
		return userRepository.save(newUser);
	}
	
	@PostMapping("/user/adduser")
    	public User addUser(@RequestBody User user) {
        // Validate the user object
        if (user.getName() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("User name and email are required");
        }
		
	@GetMapping("/users")
	List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id) 
	{
		return userRepository.findById(id)
		.orElseThrow(()->new UserNotFoundException(id)); //handling exception if user not found
		
	}
	
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) 
	{
		return userRepository.findById(id)
				.map(user ->{
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					user.setPassword(newUser.getPassword());
					return userRepository.save(user);
				}).orElseThrow(()-> new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) 
	{
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
			
		}
		userRepository.deleteById(id);
		return "User With id" + id + "has been deleted succesfully";
	}

}
