package com.ispan.aiml05.group6.project.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ispan.aiml05.group6.project.dto.PictureDTO;
import com.ispan.aiml05.group6.project.entity.Picture;

public class PictureConverterTest {
	private Decoder decoder;
	private Encoder encoder;
	private String base64Str;

	@BeforeEach
	void setup(){
		decoder = Base64.getDecoder();
		encoder = Base64.getEncoder();
		base64Str = encoder.encodeToString("Test String".getBytes());
	}

	@Test
	void testConvertPictureDTO() {
		PictureDTO dto = PictureDTO.builder()
				.id(1L)
				.createdAt(LocalDateTime.now())
				.location("Test Location")
				.base64Image(base64Str)
				.points(new double[][] {
						{ 1.0, 2.0 }, { 3.0, 4.0 }, { 5.0, 6.0 }, { 7.0, 8.0 }, { 9.0, 10.0 },
						{ 11.0, 12.0 }, { 13.0, 14.0 }, { 15.0, 16.0 }, { 17.0, 18.0 }, { 19.0, 20.0 },
						{ 21.0, 22.0 }, { 23.0, 24.0 }, { 25.0, 26.0 }, { 27.0, 28.0 }, { 29.0, 30.0 },
						{ 31.0, 32.0 }, { 33.0, 34.0 } })
				.build();
		Picture convertedPicture = PictureConverter.convert(dto);

		assertEquals(dto.getId(), convertedPicture.getId());
		assertEquals(dto.getCreatedAt(), convertedPicture.getCreatedAt());
		assertEquals(dto.getLocation(), convertedPicture.getLocation());
		assertTrue(Arrays.equals(decoder.decode(dto.getBase64Image()), convertedPicture.getImage()));

		assertEquals(dto.getPoints().length, 17);
		assertEquals(dto.getPoints()[0][0], convertedPicture.getX1(), 0.0);
		assertEquals(dto.getPoints()[0][1], convertedPicture.getY1(), 0.0);
		assertEquals(dto.getPoints()[1][0], convertedPicture.getX2(), 0.0);
		assertEquals(dto.getPoints()[1][1], convertedPicture.getY2(), 0.0);
		assertEquals(dto.getPoints()[2][0], convertedPicture.getX3(), 0.0);
		assertEquals(dto.getPoints()[2][1], convertedPicture.getY3(), 0.0);
		assertEquals(dto.getPoints()[3][0], convertedPicture.getX4(), 0.0);
		assertEquals(dto.getPoints()[3][1], convertedPicture.getY4(), 0.0);
		assertEquals(dto.getPoints()[4][0], convertedPicture.getX5(), 0.0);
		assertEquals(dto.getPoints()[4][1], convertedPicture.getY5(), 0.0);
		assertEquals(dto.getPoints()[5][0], convertedPicture.getX6(), 0.0);
		assertEquals(dto.getPoints()[5][1], convertedPicture.getY6(), 0.0);
		assertEquals(dto.getPoints()[6][0], convertedPicture.getX7(), 0.0);
		assertEquals(dto.getPoints()[6][1], convertedPicture.getY7(), 0.0);
		assertEquals(dto.getPoints()[7][0], convertedPicture.getX8(), 0.0);
		assertEquals(dto.getPoints()[7][1], convertedPicture.getY8(), 0.0);
		assertEquals(dto.getPoints()[8][0], convertedPicture.getX9(), 0.0);
		assertEquals(dto.getPoints()[8][1], convertedPicture.getY9(), 0.0);
		assertEquals(dto.getPoints()[9][0], convertedPicture.getX10(), 0.0);
		assertEquals(dto.getPoints()[9][1], convertedPicture.getY10(), 0.0);
		assertEquals(dto.getPoints()[10][0], convertedPicture.getX11(), 0.0);
		assertEquals(dto.getPoints()[10][1], convertedPicture.getY11(), 0.0);
		assertEquals(dto.getPoints()[11][0], convertedPicture.getX12(), 0.0);
		assertEquals(dto.getPoints()[11][1], convertedPicture.getY12(), 0.0);
		assertEquals(dto.getPoints()[12][0], convertedPicture.getX13(), 0.0);
		assertEquals(dto.getPoints()[12][1], convertedPicture.getY13(), 0.0);
		assertEquals(dto.getPoints()[13][0], convertedPicture.getX14(), 0.0);
		assertEquals(dto.getPoints()[13][1], convertedPicture.getY14(), 0.0);
		assertEquals(dto.getPoints()[14][0], convertedPicture.getX15(), 0.0);
		assertEquals(dto.getPoints()[14][1], convertedPicture.getY15(), 0.0);
		assertEquals(dto.getPoints()[15][0], convertedPicture.getX16(), 0.0);
		assertEquals(dto.getPoints()[15][1], convertedPicture.getY16(), 0.0);
		assertEquals(dto.getPoints()[16][0], convertedPicture.getX17(), 0.0);
		assertEquals(dto.getPoints()[16][1], convertedPicture.getY17(), 0.0);
	}

	@Test
	void testConvertPicture() {
		Picture picture = Picture.builder()
				.id(1L)
				.createdAt(LocalDateTime.now())
				.location("Test Location")
				.image(decoder.decode(base64Str))
				.x1(1.0).y1(2.0)
				.x2(3.0).y2(4.0)
				.x3(5.0).y3(6.0)
				.x4(7.0).y4(8.0)
				.x5(9.0).y5(10.0)
				.x6(11.0).y6(12.0)
				.x7(13.0).y7(14.0)
				.x8(15.0).y8(16.0)
				.x9(17.0).y9(18.0)
				.x10(19.0).y10(20.0)
				.x11(21.0).y11(22.0)
				.x12(23.0).y12(24.0)
				.x13(25.0).y13(26.0)
				.x14(27.0).y14(28.0)
				.x15(29.0).y15(30.0)
				.x16(31.0).y16(32.0)
				.x17(33.0).y17(34.0)
				.build();
		PictureDTO convertedDTO = PictureConverter.convert(picture);

		assertEquals(picture.getId(), convertedDTO.getId());
		assertEquals(picture.getCreatedAt(), convertedDTO.getCreatedAt());
		assertEquals(picture.getLocation(), convertedDTO.getLocation());
		assertTrue(Arrays.equals(picture.getImage(), decoder.decode(convertedDTO.getBase64Image())));


		assertEquals(17, convertedDTO.getPoints().length);
		assertEquals(picture.getX1(), convertedDTO.getPoints()[0][0], 0.0);
		assertEquals(picture.getY1(), convertedDTO.getPoints()[0][1], 0.0);
		assertEquals(picture.getX2(), convertedDTO.getPoints()[1][0], 0.0);
		assertEquals(picture.getY2(), convertedDTO.getPoints()[1][1], 0.0);
		assertEquals(picture.getX3(), convertedDTO.getPoints()[2][0], 0.0);
		assertEquals(picture.getY3(), convertedDTO.getPoints()[2][1], 0.0);
		assertEquals(picture.getX4(), convertedDTO.getPoints()[3][0], 0.0);
		assertEquals(picture.getY4(), convertedDTO.getPoints()[3][1], 0.0);
		assertEquals(picture.getX5(), convertedDTO.getPoints()[4][0], 0.0);
		assertEquals(picture.getY5(), convertedDTO.getPoints()[4][1], 0.0);
		assertEquals(picture.getX6(), convertedDTO.getPoints()[5][0], 0.0);
		assertEquals(picture.getY6(), convertedDTO.getPoints()[5][1], 0.0);
		assertEquals(picture.getX7(), convertedDTO.getPoints()[6][0], 0.0);
		assertEquals(picture.getY7(), convertedDTO.getPoints()[6][1], 0.0);
		assertEquals(picture.getX8(), convertedDTO.getPoints()[7][0], 0.0);
		assertEquals(picture.getY8(), convertedDTO.getPoints()[7][1], 0.0);
		assertEquals(picture.getX9(), convertedDTO.getPoints()[8][0], 0.0);
		assertEquals(picture.getY9(), convertedDTO.getPoints()[8][1], 0.0);
		assertEquals(picture.getX10(), convertedDTO.getPoints()[9][0], 0.0);
		assertEquals(picture.getY10(), convertedDTO.getPoints()[9][1], 0.0);
		assertEquals(picture.getX11(), convertedDTO.getPoints()[10][0], 0.0);
		assertEquals(picture.getY11(), convertedDTO.getPoints()[10][1], 0.0);
		assertEquals(picture.getX12(), convertedDTO.getPoints()[11][0], 0.0);
		assertEquals(picture.getY12(), convertedDTO.getPoints()[11][1], 0.0);
		assertEquals(picture.getX13(), convertedDTO.getPoints()[12][0], 0.0);
		assertEquals(picture.getY13(), convertedDTO.getPoints()[12][1], 0.0);
		assertEquals(picture.getX14(), convertedDTO.getPoints()[13][0], 0.0);
		assertEquals(picture.getY14(), convertedDTO.getPoints()[13][1], 0.0);
		assertEquals(picture.getX15(), convertedDTO.getPoints()[14][0], 0.0);
		assertEquals(picture.getY15(), convertedDTO.getPoints()[14][1], 0.0);
		assertEquals(picture.getX16(), convertedDTO.getPoints()[15][0], 0.0);
		assertEquals(picture.getY16(), convertedDTO.getPoints()[15][1], 0.0);
		assertEquals(picture.getX17(), convertedDTO.getPoints()[16][0], 0.0);
		assertEquals(picture.getY17(), convertedDTO.getPoints()[16][1], 0.0);
	}
}
