package com.ispan.aiml05.group6.project.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ispan.aiml05.group6.project.exception.PictureDTOException;
import com.ispan.aiml05.group6.project.exception.PictureNotFoundException;
import com.ispan.aiml05.group6.project.exception.PictureSaveException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GlobalExceptionHandlerTests {

	private GlobalExceptionHandler exceptionHandler;

	@BeforeEach
	public void setup() {
		exceptionHandler = new GlobalExceptionHandler();
	}

	@Test
	public void testHandlePictureNotFoundException() {
		PictureNotFoundException exception = new PictureNotFoundException("Picture not found");

		ResponseEntity<String> response = exceptionHandler.handlePictureNotFoundException(exception);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Picture not found", response.getBody());
	}

	@Test
	public void testHandlePictureDTOException() {
		PictureDTOException exception = new PictureDTOException("PictureDTO is null");

		ResponseEntity<String> response = exceptionHandler.handlePictureDTOException(exception);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("PictureDTO is null", response.getBody());
	}

	@Test
	public void testHandlePictureSaveException() {
		PictureSaveException exception = new PictureSaveException("Failed to save picture");

		ResponseEntity<String> response = exceptionHandler.handlePictureSaveException(exception);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Failed to save picture", response.getBody());
	}
}
