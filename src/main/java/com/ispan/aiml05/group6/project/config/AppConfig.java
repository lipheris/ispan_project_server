package com.ispan.aiml05.group6.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
public class AppConfig {
	@Getter
	@Value("${pictures.storage.path}")
    private String picStoragePath;

	@Getter
	@Value("${jwt.secret-key}")
	private String secretKey;

	@Getter
    @Value("${jwt.expiration-time}")
    private long expirationTime;
}
