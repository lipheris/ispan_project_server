package com.ispan.aiml05.group6.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ispan.aiml05.group6.project.dao.UserRepo;
import com.ispan.aiml05.group6.project.dto.UserDTO;
import com.ispan.aiml05.group6.project.entity.User;
import com.ispan.aiml05.group6.project.exception.LoginFailedException;
import com.ispan.aiml05.group6.project.util.JwtUtil;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;

	public String login(UserDTO userDto) {
		// TODO Auto-generated method stub
		User loginUser = userRepo.findByEmail(userDto.getEmail()).orElseThrow(() -> new LoginFailedException("帳號或密碼錯誤"));
		if(passwordEncoder.matches(userDto.getPassword(), loginUser.getPassword())){
			return jwtUtil.generateToken(loginUser);
		}else{
			throw new LoginFailedException("帳號或密碼錯誤");
		}
	}

}
