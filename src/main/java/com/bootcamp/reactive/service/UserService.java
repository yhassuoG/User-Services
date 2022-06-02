package com.bootcamp.reactive.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.bootcamp.reactive.config.CacheConfig;
import com.bootcamp.reactive.dto.UserDto;
import com.bootcamp.reactive.entity.User;
import com.bootcamp.reactive.repository.UserRepository;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;

@Service
public class UserService {

	/*@Bean
	public Supplier<User> saveUser(){
		
		return () -> new User("DNI","74178794",930136324,"5654454343","jonathan@gmail.com");
	}*/
	
	@Autowired
	private StreamBridge streamBridge;
	
	@Autowired
	private UserRepository repository;
	
	public User saveUser(UserDto userDto) {
		
		User user = userDto.getUser();
		streamBridge.send("output-out-0", userDto);
		return repository.save(user);
	}
	
	@Cacheable(cacheNames = CacheConfig.USER_CACHE, key="#id", unless="#result == null")
	public User findById(Long id) {
		
		
		streamBridge.send("output-out-0", id);
		return repository.findById(id).orElse(null);
	}
	
	
}
