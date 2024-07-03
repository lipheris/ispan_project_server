package com.ispan.aiml05.group6.project.exception;

public class LoginFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public LoginFailedException(String message){
		super(message);
	}
}
