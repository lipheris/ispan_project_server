package com.ispan.aiml05.group6.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.aiml05.group6.project.dto.UserDTO;
import com.ispan.aiml05.group6.project.service.UserService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
    private UserService userService;

	@PostMapping
	public String login(@RequestBody UserDTO userDto){
		String userToken = userService.login(userDto);
		return userToken;
	}
}
