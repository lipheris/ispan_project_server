package com.ispan.aiml05.group6.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.aiml05.group6.project.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

}
