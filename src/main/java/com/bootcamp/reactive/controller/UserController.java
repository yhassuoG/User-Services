package com.bootcamp.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.reactive.dto.UserDto;
import com.bootcamp.reactive.entity.User;
import com.bootcamp.reactive.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping
	public User saveUser(@RequestBody UserDto userDto) {
		
		return service.saveUser(userDto);
	}
	
	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		
		return service.findById(id);
	}
}
