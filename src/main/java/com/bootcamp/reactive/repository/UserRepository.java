package com.bootcamp.reactive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.reactive.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
