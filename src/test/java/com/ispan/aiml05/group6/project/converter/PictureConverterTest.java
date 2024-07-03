package com.ispan.aiml05.group6.project.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ispan.aiml05.group6.project.config.AppConfig;
import com.ispan.aiml05.group6.project.dto.PictureDTO;
import com.ispan.aiml05.group6.project.entity.Picture;
import com.ispan.aiml05.group6.project.exception.PictureDTOException;
import com.ispan.aiml05.group6.project.exception.PictureSaveException;
import com.ispan.aiml05.group6.project.service.FileService;

@ExtendWith(MockitoExtension.class)
public class PictureConverterTest {

	@Mock
	private AppConfig appConfig;

	@Mock
	private FileService fileService;

	@InjectMocks
	private PictureConverter pictureConverter;

	private Picture picture;
	private PictureDTO pictureDTO;
	private byte[] imageData;

	@BeforeEach
	public void setup() {
		LocalDateTime now = LocalDateTime.now();
		pictureDTO = PictureDTO.builder()
				.id(1L)
				.createdAt(now)
				.location("Test Location")
				.base64Image(Base64.getEncoder().encodeToString("test image".getBytes()))
				.points(new double[17][2])
				.build();
		imageData = Base64.getDecoder().decode(pictureDTO.getBase64Image());
		picture = Picture.builder()
				.id(1L)
				.createdAt(now)
				.location("Test Location")
				.path("test/path/image.jpg")
				.build();
	}

	@Test
	public void testConvertPictureDTOtoPictureSuccess() throws PictureDTOException {
		when(appConfig.getPicStoragePath()).thenReturn("/test/path");

		Picture picture = pictureConverter.convert(pictureDTO);

		assertNotNull(picture);
		assertEquals(pictureDTO.getId(), picture.getId());
		assertEquals(pictureDTO.getCreatedAt(), picture.getCreatedAt());
		assertEquals(pictureDTO.getLocation(), picture.getLocation());
		String expectedPathPrefix = Paths.get("/test/path").toString();
		assertTrue(picture.getPath().startsWith(expectedPathPrefix));
	}

	@Test
	public void testConvertPictureDTOtoPictureNullDTO() {
		PictureDTO nullDTO = null;

		PictureDTOException exception = assertThrows(PictureDTOException.class, () -> {
			pictureConverter.convert(nullDTO);
		});

		assertEquals("PictureDTO is null", exception.getMessage());
	}

	@Test
	public void testConvertInvalidBase64Image() {
		pictureDTO.setBase64Image("invalid base64");

		PictureDTOException exception = assertThrows(PictureDTOException.class, () -> {
			pictureConverter.convert(pictureDTO);
		});

		assertEquals("Invalid Base64 image data", exception.getMessage());
	}

	@Test
	public void testConvertLargeImage() {
		byte[] largeImage = new byte[16_000_001];
		pictureDTO.setBase64Image(Base64.getEncoder().encodeToString(largeImage));

		PictureDTOException exception = assertThrows(PictureDTOException.class, () -> {
			pictureConverter.convert(pictureDTO);
		});

		assertEquals("Image too large", exception.getMessage());
	}

	@Test
	public void testSavePictureToDirSuccess()
			throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		when(appConfig.getPicStoragePath()).thenReturn("/test/path");

		Path dirPath = Paths.get("/test/path", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		Path filePath = dirPath.resolve(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + ".jpg");

		doNothing().when(fileService).createDirectories(dirPath);
		doNothing().when(fileService).write(filePath, imageData);

		Method savePictureToDirMethod = PictureConverter.class.getDeclaredMethod("savePictureToDir", byte[].class);
		savePictureToDirMethod.setAccessible(true);
		String savedPath = (String)savePictureToDirMethod.invoke(pictureConverter, imageData);


		assertNotNull(savedPath);
		assertTrue(savedPath.startsWith(Paths.get("/test/path").toString()));
	}

	@Test
	public void testSavePictureToDirException()
			throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		when(appConfig.getPicStoragePath()).thenReturn("/test/path");

		Path dirPath = Paths.get("/test/path", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

		doThrow(IOException.class).when(fileService).createDirectories(dirPath);

		Method savePictureToDirMethod = PictureConverter.class.getDeclaredMethod("savePictureToDir", byte[].class);
		savePictureToDirMethod.setAccessible(true);
		try {
			savePictureToDirMethod.invoke(pictureConverter, imageData);
			fail("Expected PictureSaveException to be thrown"); // Fail the test if no exception is thrown
		} catch (InvocationTargetException e) {
			Throwable targetException = e.getTargetException();
			if (targetException instanceof PictureSaveException) {
				assertEquals("Failed to save picture", targetException.getMessage());
			} else {
				fail("Unexpected exception thrown: " + targetException.getClass().getName());
			}
		}
		PictureSaveException exception = assertThrows(PictureSaveException.class, () -> {
			pictureConverter.convert(pictureDTO);
		});

		assertEquals("Failed to save picture", exception.getMessage());
	}

	@Test
	public void testConvertPictureDtoValid() {
		PictureDTO result = pictureConverter.convert(picture);
		PictureDTO expectedPictureDto = PictureDTO.builder()
			.id(picture.getId())
			.createdAt(picture.getCreatedAt())
			.location(picture.getLocation())
			.path(picture.getPath()).build();
		assertEquals(expectedPictureDto, result);
	}
	@Test
    public void testConvertPictureDtoInvalid() {
		final Picture nullPicture = null;
		assertThrows(PictureDTOException.class, ()->pictureConverter.convert(nullPicture));
	}
}
