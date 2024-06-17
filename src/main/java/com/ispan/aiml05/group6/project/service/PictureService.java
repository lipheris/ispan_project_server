package com.ispan.aiml05.group6.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.aiml05.group6.project.converter.PictureConverter;
import com.ispan.aiml05.group6.project.dao.PictureRepo;
import com.ispan.aiml05.group6.project.dto.PictureDTO;
import com.ispan.aiml05.group6.project.entity.Picture;
import com.ispan.aiml05.group6.project.exception.PictureNotFoundException;

@Service
public class PictureService {

	
	private final PictureRepo pictureRepo;
	
	@Autowired
	public PictureService(PictureRepo pictureRepo) {
		this.pictureRepo = pictureRepo;
	}

	public Picture createPicture(PictureDTO pictureDto) {
		Picture picture = PictureConverter.convert(pictureDto);
        return pictureRepo.save(picture);
	}

	public Picture getPictureById(long id) {
		return pictureRepo.findById(id).orElseThrow(() -> new PictureNotFoundException("Picture not found with id " + id));
	}
	
	public List<Picture> getAllPictures() {
		return pictureRepo.findAll();
	}
	
	public Picture updatePicture(PictureDTO pictureDto) {
		Picture picture = PictureConverter.convert(pictureDto);
		return pictureRepo.save(picture);
	}
	
	public void deletePictureById(long id) {
		pictureRepo.deleteById(id);
	}
}
