package com.ispan.aiml05.group6.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.aiml05.group6.project.dto.PictureDTO;
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
	public Picture getPictureById(@PathVariable long id) {
		return pictureService.getPictureById(id);
	}

	@GetMapping
	public List<PictureDTO> getAllPictures() {
		return pictureService.getAllPictures();
	}

	@GetMapping("/search")
	public List<PictureDTO> getPicturesByDateRangeAndLoc(
		@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
		@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, 
		@RequestParam("location") String location){
		return pictureService.getPicturesByDateRangeAndLoc(startDate, endDate, location);
	}

	@PostMapping
	public PictureDTO createPicture(@RequestBody PictureDTO pictureDto) {
		return pictureService.createPicture(pictureDto);
	}

	@PutMapping("/{id}")
	public PictureDTO updatePicture(@PathVariable long id, @RequestBody PictureDTO pictureDto) {
		return pictureService.updatePicture(pictureDto);
	}

	@DeleteMapping("/{id}")
	public void deletePicture(@PathVariable long id) {
		pictureService.deletePictureById(id);
	}
}
