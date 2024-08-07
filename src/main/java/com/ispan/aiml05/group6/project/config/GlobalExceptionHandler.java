package com.ispan.aiml05.group6.project.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ispan.aiml05.group6.project.exception.PictureDTOException;
import com.ispan.aiml05.group6.project.exception.PictureNotFoundException;
import com.ispan.aiml05.group6.project.exception.PictureSaveException;

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

	@ExceptionHandler(PictureSaveException.class)
    public ResponseEntity<String> handlePictureSaveException(PictureSaveException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
