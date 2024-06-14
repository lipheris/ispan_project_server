package com.ispan.aiml05.group6.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.aiml05.group6.project.dao.PictureDao;
import com.ispan.aiml05.group6.project.entity.Picture;

@Service
public class PictureService {

	
	private final PictureDao pictureDao;
	
	@Autowired
	public PictureService(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	public Picture createPicture(Picture picture) {
		return pictureDao.save(picture);
	}

	public Picture getPictureById(long id) {
		return pictureDao.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public List<Picture> getAllPictures() {
		return pictureDao.findAll();
	}
	
	public Picture updatePicture(Picture picture) {
		return pictureDao.save(picture);
	}
	
	public void deletePictureById(long id) {
		pictureDao.deleteById(id);
	}
}
