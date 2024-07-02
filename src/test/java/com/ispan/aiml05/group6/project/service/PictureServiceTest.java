package com.ispan.aiml05.group6.project.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ispan.aiml05.group6.project.converter.PictureConverter;
import com.ispan.aiml05.group6.project.dao.PictureRepo;
import com.ispan.aiml05.group6.project.dto.PictureDTO;
import com.ispan.aiml05.group6.project.entity.Picture;
import com.ispan.aiml05.group6.project.exception.PictureDTOException;
import com.ispan.aiml05.group6.project.exception.PictureNotFoundException;

class PictureServiceTest {

        @Mock
        private PictureRepo pictureRepo;

        @Mock
        private PictureConverter pictureConverter;

        @InjectMocks
        private PictureService pictureService;

        @BeforeEach
        void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        void testCreatePicture() {
                PictureDTO pictureDto = PictureDTO.builder()
                                .location("Test Location")
                                .points(new double[17][2])
                                .build();
                Picture picture = Picture.builder().location("Test Location").build();
                when(pictureConverter.convert(pictureDto)).thenReturn(picture);
                when(pictureConverter.convert(picture)).thenReturn(pictureDto);
                when(pictureRepo.save(picture)).thenReturn(picture);
                PictureDTO result = pictureService.createPicture(pictureDto);

                assertNotNull(result);
                assertEquals(pictureDto.getId(), result.getId());
                assertEquals(pictureDto.getCreatedAt(), result.getCreatedAt());
                assertEquals(pictureDto.getLocation(), result.getLocation());
                assertArrayEquals(pictureDto.getPoints(), result.getPoints());
                verify(pictureRepo, times(1)).save(picture);
        }

        @Test
        void testCreatePictureWithNullDTO() {
                PictureDTO pictureDto = null;
                assertThrows(PictureDTOException.class, () -> pictureService.createPicture(pictureDto));
        }

        @Test
        void testGetPictureById() {
                Picture picture = Picture.builder().id(1L).build();
                when(pictureRepo.findById(1L)).thenReturn(Optional.of(picture));

                Picture foundPicture = pictureService.getPictureById(1L);

                assertEquals(picture, foundPicture);
                verify(pictureRepo, times(1)).findById(1L);
        }

        @Test
        void testGetPictureByIdNotFound() {
                when(pictureRepo.findById(1L)).thenReturn(Optional.empty());

                PictureNotFoundException exception = assertThrows(PictureNotFoundException.class,
                                () -> pictureService.getPictureById(1L));
                assertEquals("Picture not found with id 1", exception.getMessage());
        }

        @Test
        void testGetAllPictures() {
                LocalDateTime now = LocalDateTime.now();
                Picture picture1 = Picture.builder()
                                .id(1L)
                                .createdAt(now)
                                .location("test location")
                                .path("test path")
                                .x1(0.1).y1(0.2).build();
                Picture picture2 = Picture.builder()
                                .id(2L)
                                .createdAt(now)
                                .location("test location2")
                                .path("test path2")
                                .x1(0.3).y1(0.4).build();
                List<Picture> pictures = List.of(picture1, picture2);
                double[][] points1 = new double[][] { { 0.1, 0.2 } };
                PictureDTO pictureDto1 = PictureDTO.builder()
                                .id(1L)
                                .createdAt(now)
                                .location("test location")
                                .path("test path")
                                .points(points1)
                                .build();
                double[][] points2 = new double[][] { { 0.3, 0.4 } };
                PictureDTO pictureDto2 = PictureDTO.builder()
                                .id(2L)
                                .createdAt(now)
                                .location("test location2")
                                .path("test path2")
                                .points(points2)
                                .build();
                when(pictureRepo.findAll()).thenReturn(pictures);
                when(pictureConverter.convert(picture1)).thenReturn(pictureDto1);
                when(pictureConverter.convert(picture2)).thenReturn(pictureDto2);
                List<PictureDTO> result = pictureService.getAllPictures();
                assertNotNull(result);
                assertEquals(2, result.size());

                PictureDTO actualPictureDto1 = result.get(0);
                assertEquals(pictureDto1.getId(), actualPictureDto1.getId());
                assertEquals(pictureDto1.getCreatedAt(), actualPictureDto1.getCreatedAt());
                assertEquals(pictureDto1.getLocation(), actualPictureDto1.getLocation());
                assertArrayEquals(pictureDto1.getPoints(), actualPictureDto1.getPoints());

                PictureDTO actualPictureDto2 = result.get(1);
                assertEquals(pictureDto2.getId(), actualPictureDto2.getId());
                assertEquals(pictureDto2.getCreatedAt(), actualPictureDto2.getCreatedAt());
                assertEquals(pictureDto2.getLocation(), actualPictureDto2.getLocation());
                assertArrayEquals(pictureDto2.getPoints(), actualPictureDto2.getPoints());

                verify(pictureRepo, times(1)).findAll();
        }

        @Test
        void testReplacePictureWithNullDTO() {
                assertThrows(PictureDTOException.class, () -> pictureService.replacePicture(1L, null));
        }

        @Test
        void testReplacePictureIdDoesNotExist() {
                PictureDTO pictureDTO = PictureDTO.builder()
                                .location("Test Location")
                                .points(new double[17][2])
                                .build();
                when(pictureService.existsById(1L)).thenReturn(true);
                when(pictureService.createPicture(pictureDTO)).thenReturn(pictureDTO);
                PictureDTO result = pictureService.replacePicture(1L, pictureDTO);
                assertEquals(pictureDTO, result);

        }

        @Test
        void testReplacePictureIdExist(){
                PictureDTO pictureDTO = PictureDTO.builder()
                                .location("Test Location")
                                .points(new double[17][2])
                                .build();
                Picture picture = Picture.builder().location("Test Location").build();

                when(pictureService.existsById(1L)).thenReturn(false);
                when(pictureConverter.convert(pictureDTO)).thenReturn(picture);
                when(pictureConverter.convert(picture)).thenReturn(pictureDTO);
                when(pictureRepo.save(picture)).thenReturn(picture);

                PictureDTO result = pictureService.replacePicture(1L, pictureDTO);
                assertEquals(pictureDTO, result);

                verify(pictureRepo, times(1)).save(picture);
                verify(pictureConverter, times(1)).convert(picture);
        }

        @Test
        void testUpdatePicture() {
                LocalDateTime now = LocalDateTime.now();
                PictureDTO pictureDto = PictureDTO.builder()
                                .id(1L)
                                .createdAt(now)
                                .location("Updated Location")
                                .points(new double[17][2])
                                .build();
                Picture picture = Picture.builder()
                                .id(1L)
                                .createdAt(now)
                                .location("Updated Location")
                                .build();

                when(pictureConverter.convert(pictureDto)).thenReturn(picture);
                when(pictureConverter.convert(picture)).thenReturn(pictureDto);
                when(pictureRepo.existsById(1L)).thenReturn(true);
                when(pictureRepo.save(picture)).thenReturn(picture);

                PictureDTO result = pictureService.updatePicture(1L, pictureDto);

                assertNotNull(result);
                assertEquals(pictureDto.getId(), result.getId());
                assertEquals(pictureDto.getCreatedAt(), result.getCreatedAt());
                assertEquals(pictureDto.getLocation(), result.getLocation());
                assertArrayEquals(pictureDto.getPoints(), result.getPoints());

                verify(pictureRepo, times(1)).existsById(1L);
                verify(pictureRepo, times(1)).save(any(Picture.class));
        }

        @Test
        void testUpdatePictureWithNullDTO() {
                when(pictureRepo.existsById(1L)).thenReturn(true);

                PictureDTOException e = assertThrows(PictureDTOException.class,
                                () -> pictureService.updatePicture(1L, null));
                assertEquals("PictureDTO is null", e.getMessage());
        }

        @Test
        void testDeletePictureById() {
                Picture picture = Picture.builder().id(1L).build();

                when(pictureRepo.findById(1L)).thenReturn(Optional.of(picture));

                pictureService.deletePictureById(1L);

                verify(pictureRepo, times(1)).findById(1L);
                verify(pictureRepo, times(1)).deleteById(1L);
        }

        @Test
        void testDeletePictureByIdNotExists() {
                when(pictureRepo.findById(1L)).thenReturn(Optional.empty());

                PictureNotFoundException e = assertThrows(PictureNotFoundException.class,
                                () -> pictureService.deletePictureById(1L));

                assertEquals("Picture 1 does not exist", e.getMessage());

                verify(pictureRepo, times(1)).findById(1L);
        }

        @Test
        public void testGetPicturesByDateRangeAndLoc() {

                LocalDate startDate = LocalDate.of(2024, 6, 1);
                LocalDate endDate = LocalDate.of(2024, 6, 30);
                LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
                LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
                String location = "Test Location";

                Picture picture1 = Picture.builder()
                                .id(1L)
                                .createdAt(LocalDateTime.of(2024, 6, 10, 12, 0))
                                .location(location).build();

                Picture picture2 = Picture.builder()
                                .id(2L)
                                .createdAt(LocalDateTime.of(2024, 6, 20, 10, 0))
                                .location(location).build();

                Picture picture3 = Picture.builder()
                                .id(3L)
                                .createdAt(LocalDateTime.of(2024, 7, 1, 10, 0))
                                .location(location).build();

                Picture picture4 = Picture.builder()
                                .id(4L)
                                .createdAt(LocalDateTime.of(2024, 6, 20, 10, 0))
                                .location(location).build();

                List<Picture> mockPictures = Arrays.asList(picture1, picture2);
                PictureDTO pictureDto1 = PictureDTO.builder()
                                .id(1L)
                                .createdAt(LocalDateTime.of(2024, 6, 10, 12, 0))
                                .location(location).build();
                PictureDTO pictureDto2 = PictureDTO.builder()
                                .id(2L)
                                .createdAt(LocalDateTime.of(2024, 6, 20, 10, 0))
                                .location(location).build();
                PictureDTO pictureDto3 = PictureDTO.builder()
                                .id(3L)
                                .createdAt(LocalDateTime.of(2024, 7, 1, 10, 0))
                                .location(location).build();
                PictureDTO pictureDto4 = PictureDTO.builder()
                                .id(4L)
                                .createdAt(LocalDateTime.of(2024, 6, 20, 10, 0))
                                .location(location).build();

                when(pictureRepo.findPicturesByCreatedAtRangeAndLocation(startDateTime,
                                endDateTime, location))
                                .thenReturn(mockPictures);
                when(pictureConverter.convert(picture1)).thenReturn(pictureDto1);
                when(pictureConverter.convert(picture2)).thenReturn(pictureDto2);
                when(pictureConverter.convert(picture3)).thenReturn(pictureDto3);
                when(pictureConverter.convert(picture4)).thenReturn(pictureDto4);

                List<PictureDTO> result = pictureService.getPicturesByDateRangeAndLoc(startDate,
                                endDate, location);

                assertNotNull(result);
                assertEquals(2, result.size());
                assertTrue(result.contains(pictureConverter.convert(picture1)));
                assertEquals(1L, result.get(0).getId());
                assertTrue(startDateTime.isBefore(result.get(0).getCreatedAt()));
                assertTrue(endDateTime.isAfter(result.get(0).getCreatedAt()));
                assertEquals(location, result.get(0).getLocation());
                assertTrue(result.contains(pictureConverter.convert(picture2)));
                assertEquals(2L, result.get(1).getId());
                assertTrue(startDateTime.isBefore(result.get(1).getCreatedAt()));
                assertTrue(endDateTime.isAfter(result.get(1).getCreatedAt()));
                assertEquals(location, result.get(1).getLocation());
                assertFalse(result.contains(pictureConverter.convert(picture3)));
                assertFalse(result.contains(pictureConverter.convert(picture4)));

                verify(pictureRepo, times(1)).findPicturesByCreatedAtRangeAndLocation(
                                startDateTime, endDateTime, location);
        }

        @Test
        public void testExistsById() {
                when(pictureRepo.existsById(1L)).thenReturn(true);
                boolean result = pictureService.existsById(1L);
                assertTrue(result);
        }

        @Test
        public void testExistsByIdNotExists() {
                when(pictureRepo.existsById(2L)).thenReturn(false);
                boolean result = pictureService.existsById(2L);
                assertFalse(result);
        }
}
