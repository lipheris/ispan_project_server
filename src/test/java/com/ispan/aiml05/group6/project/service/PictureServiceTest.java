package com.ispan.aiml05.group6.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ispan.aiml05.group6.project.dao.PictureRepo;
import com.ispan.aiml05.group6.project.dto.PictureDTO;
import com.ispan.aiml05.group6.project.entity.Picture;
import com.ispan.aiml05.group6.project.exception.PictureDTOException;
import com.ispan.aiml05.group6.project.exception.PictureNotFoundException;

class PictureServiceTest {

    @Mock
    private PictureRepo pictureRepo;

    @InjectMocks
    private PictureService pictureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePicture() {
        Picture picture = new Picture();
        when(pictureRepo.save(picture)).thenReturn(picture);

        PictureDTO pictureDto = PictureDTO.builder()
            .points(new double[17][2])
            .build();
        Picture createdPicture = pictureService.createPicture(pictureDto);

        assertEquals(picture, createdPicture);
        verify(pictureRepo, times(1)).save(picture);
    }

    @Test
    void testCreatePictureWithNullDTO(){
        PictureDTO pictureDto = null;
        assertThrows(PictureDTOException.class, () -> pictureService.createPicture(pictureDto));
    }

    @Test
    void testGetPictureById() {
        Picture picture = new Picture();
        when(pictureRepo.findById(1L)).thenReturn(Optional.of(picture));

        Picture foundPicture = pictureService.getPictureById(1L);

        assertEquals(picture, foundPicture);
    }

	@Test
	void testGetPictureByIdNotFound() {
        when(pictureRepo.findById(1L)).thenReturn(Optional.empty());

        PictureNotFoundException exception = assertThrows(PictureNotFoundException.class, () -> pictureService.getPictureById(1L));
		assertTrue(exception.getMessage().contains("Picture not found with id 1"));
    }

    @Test
    void testGetAllPictures() {
        List<Picture> pictures = List.of(new Picture(), new Picture());
        when(pictureRepo.findAll()).thenReturn(pictures);

        List<Picture> allPictures = pictureService.getAllPictures();

        assertEquals(pictures, allPictures);
    }

    @Test
    void testUpdatePicture() {
        Picture picture = new Picture();
        when(pictureRepo.save(picture)).thenReturn(picture);
        PictureDTO pictureDto = PictureDTO.builder()
            .points(new double[17][2])
            .build();
        Picture updatedPicture = pictureService.updatePicture(pictureDto);

        assertEquals(picture, updatedPicture);
    }

    @Test
    void testUpdatePictureWithNullDTO(){
        PictureDTO pictureDto = null;
        assertThrows(PictureDTOException.class, () -> pictureService.updatePicture(pictureDto));
    }

    @Test
    void testDeletePictureById() {
        pictureService.deletePictureById(1L);
        verify(pictureRepo, times(1)).deleteById(1L);
    }
}
