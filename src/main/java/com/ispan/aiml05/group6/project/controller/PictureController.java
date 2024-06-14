package com.ispan.aiml05.group6.project.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.aiml05.group6.project.entity.Picture;
import com.ispan.aiml05.group6.project.service.PictureService;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {
	
	private final PictureService pictureService;

	@Autowired
	public PictureController(PictureService pictureService) {
		this.pictureService = pictureService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Picture> getPictureById(@PathVariable long id) {
		try {
			Picture picture = pictureService.getPictureById(id);
			return ResponseEntity.ok(picture);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public List<Picture> getAllPicture() {
		return pictureService.getAllPictures();
	}
	
	@PostMapping()
	public ResponseEntity<Picture> createPicture(@RequestBody Picture picture) {
		try {
			Picture createdPicture = pictureService.createPicture(picture);
			return ResponseEntity
					.created(new URI("/api/pictures/"+createdPicture.getId())).body(createdPicture);
		} catch (URISyntaxException e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//TODO
	@PutMapping("/{id}")
	public Picture updatePicture(@PathVariable long id, @RequestBody Picture picture) {
		return pictureService.updatePicture(picture);
	}
	
	@DeleteMapping("/{id}")
	public void deletePicture(@PathVariable long id) {
		pictureService.deletePictureById(id);
	}
}
