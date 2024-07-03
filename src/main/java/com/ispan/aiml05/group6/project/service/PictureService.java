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
	private final PictureConverter pictureConverter;
	
	@Autowired
	public PictureService(PictureRepo pictureRepo, PictureConverter pictureConverter) {
		this.pictureRepo = pictureRepo;
		this.pictureConverter = pictureConverter;
	}

	public PictureDTO createPicture(PictureDTO pictureDto) {
		pictureDto = Optional.ofNullable(pictureDto).orElseThrow(() -> new PictureDTOException("PictureDTO is null"));
		pictureDto.setId(0);
		Picture picture = pictureConverter.convert(pictureDto);
        picture = pictureRepo.save(picture);
		return pictureConverter.convert(picture);
	}

	public PictureDTO getPictureById(long id) {
		return pictureRepo.findById(id).map(pictureConverter::convert).orElseThrow(() -> new PictureNotFoundException("Picture not found with id " + id));
	}
	
	public List<PictureDTO> getAllPictures() {
		return pictureRepo.findAll().stream().map(pictureConverter::convert).toList();
	}
	
	public PictureDTO replacePicture(long id, PictureDTO pictureDto){
		pictureDto = Optional.ofNullable(pictureDto).orElseThrow(() -> new PictureDTOException("PictureDTO is null"));
		if(!existsById(id)){
			pictureDto.setId(0);
            return createPicture(pictureDto);
        }else{
			Picture picture = pictureRepo.save(pictureConverter.convert(pictureDto));
			return pictureConverter.convert(picture);
		}
	}

	public PictureDTO updatePicture(long id, PictureDTO pictureDto) {
		if(!pictureRepo.existsById(id))
			throw new PictureNotFoundException("Picture "+ id +" does not exist");
		pictureDto = Optional.ofNullable(pictureDto).orElseThrow(() -> new PictureDTOException("PictureDTO is null"));
		Picture picture = pictureRepo.save(pictureConverter.convert(pictureDto));
		return pictureConverter.convert(picture);
	}
	
	public void deletePictureById(long id) {
		pictureRepo.findById(id).orElseThrow(() -> new PictureNotFoundException("Picture "+ id +" does not exist"));
		pictureRepo.deleteById(id);
	}

	public List<PictureDTO> getPicturesByDateRangeAndLoc(LocalDate startDate, LocalDate endDate, String location) {
		LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
		LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
		List<Picture> pictures = pictureRepo.findPicturesByCreatedAtRangeAndLocation(startDateTime, endDateTime, location);
		return pictures.stream().map(pictureConverter::convert).toList();
	}

	public boolean existsById(long id) {
		return pictureRepo.existsById(id);
	}
}
