package com.ispan.aiml05.group6.project.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ispan.aiml05.group6.project.exception.PictureDTOException;
import com.ispan.aiml05.group6.project.exception.PictureNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(PictureNotFoundException.class)
    public ResponseEntity<String> handlePictureNotFoundException(PictureNotFoundException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PictureDTOException.class)
    public ResponseEntity<String> handlePictureDTOException(PictureDTOException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
