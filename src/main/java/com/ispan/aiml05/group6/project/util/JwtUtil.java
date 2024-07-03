package com.ispan.aiml05.group6.project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ispan.aiml05.group6.project.config.AppConfig;
import com.ispan.aiml05.group6.project.entity.User;

@Component
public class JwtUtil {
	private AppConfig appConfig;
	@Autowired
    public JwtUtil(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
	public String generateToken(User loginUser) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'generateToken'");
	}

}
