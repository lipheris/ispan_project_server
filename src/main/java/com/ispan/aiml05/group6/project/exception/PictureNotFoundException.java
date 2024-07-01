package com.ispan.aiml05.group6.project.exception;

public class PictureNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PictureNotFoundException(String message) {
		super(message);
	}
	
}
