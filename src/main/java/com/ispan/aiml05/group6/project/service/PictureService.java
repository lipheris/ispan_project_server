package com.ispan.aiml05.group6.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.aiml05.group6.project.converter.PictureConverter;
import com.ispan.aiml05.group6.project.dao.PictureRepo;
import com.ispan.aiml05.group6.project.dto.PictureDTO;
import com.ispan.aiml05.group6.project.entity.Picture;
import com.ispan.aiml05.group6.project.exception.PictureDTOException;
import com.ispan.aiml05.group6.project.exception.PictureNotFoundException;

@Service
public class PictureService {

	
	private final PictureRepo pictureRepo;
	
	@Autowired
	public PictureService(PictureRepo pictureRepo) {
		this.pictureRepo = pictureRepo;
	}

	public PictureDTO createPicture(PictureDTO pictureDto) {
		pictureDto = Optional.ofNullable(pictureDto).orElseThrow(() -> new PictureDTOException("PictureDTO is null"));
		Picture picture = PictureConverter.convert(pictureDto);
        pictureRepo.save(picture);
		return PictureConverter.convert(picture);
	}

	public Picture getPictureById(long id) {
		return pictureRepo.findById(id).orElseThrow(() -> new PictureNotFoundException("Picture not found with id " + id));
	}
	
	public List<PictureDTO> getAllPictures() {
		return pictureRepo.findAll().stream().map(PictureConverter::convert).toList();
	}
	
	public PictureDTO updatePicture(PictureDTO pictureDto) {
		pictureDto = Optional.ofNullable(pictureDto).orElseThrow(() -> new PictureDTOException("PictureDTO is null"));
		Picture picture = pictureRepo.findById(pictureDto.getId()).orElseThrow(() -> new PictureNotFoundException("Picture does not exist"));
		picture = pictureRepo.save(PictureConverter.convert(pictureDto));
		return PictureConverter.convert(picture);
	}
	
	public void deletePictureById(long id) {
		pictureRepo.findById(id).orElseThrow(() -> new PictureNotFoundException("Picture "+ id +" does not exist"));
		pictureRepo.deleteById(id);
	}

	public List<PictureDTO> getPicturesByDateRangeAndLoc(LocalDate startDate, LocalDate endDate, String location) {
		LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
		LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
		List<Picture> pictures = pictureRepo.findPicturesByCreatedAtRangeAndLocation(startDateTime, endDateTime, location);
		return pictures.stream().map(PictureConverter::convert).toList();
	}
}
