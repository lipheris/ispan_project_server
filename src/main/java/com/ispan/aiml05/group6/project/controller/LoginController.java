package com.ispan.aiml05.group6.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.aiml05.group6.project.dto.UserDTO;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@PostMapping
	public ResponseEntity<String> login(@RequestBody UserDTO userDto){
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
}
