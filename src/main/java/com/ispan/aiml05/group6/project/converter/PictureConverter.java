package com.ispan.aiml05.group6.project.converter;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import com.ispan.aiml05.group6.project.dto.PictureDTO;
import com.ispan.aiml05.group6.project.entity.Picture;
import com.ispan.aiml05.group6.project.exception.PictureDTOException;

public class PictureConverter {
	private static final Decoder decoder = Base64.getDecoder();
	private static final Encoder encoder = Base64.getEncoder();

	public static Picture convert(PictureDTO pictureDto) throws PictureDTOException {
		if (pictureDto == null) {
			throw new PictureDTOException("PictureDTO is null");
		}

		byte[] image = null;

		if (pictureDto.getBase64Image() != null) {
			try {
				image = decoder.decode(pictureDto.getBase64Image());
			} catch (IllegalArgumentException e) {
				throw new PictureDTOException("Invalid Base64 image data");
			}

			if (image.length > 16_000_000) { // 假設最大允許大小為16MB
				throw new PictureDTOException("Image too large");
			}
		}
		Picture convertedPicture = Picture.builder()
				.id(pictureDto.getId())
				.createdAt(pictureDto.getCreatedAt())
				.location(pictureDto.getLocation())
				.image(image)
				.build();
		double[][] points = pictureDto.getPoints();
		try {
			for (int i = 1; i <= Math.min(points.length, 17); i++) {
				String xFieldName = "x" + i;
				String yFieldName = "y" + i;
				Field xField = Picture.class.getDeclaredField(xFieldName);
				Field yField = Picture.class.getDeclaredField(yFieldName);
				// 取消私有屬性的封裝性
				xField.setAccessible(true);
				yField.setAccessible(true);
				xField.set(convertedPicture, points[i - 1][0]);
				yField.set(convertedPicture, points[i - 1][1]);
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new PictureDTOException("PictureDTO converter failed");

		}
		return convertedPicture;
	}

	public static PictureDTO convert(Picture picture) {
		if (picture == null) {
			throw new PictureDTOException("Picture is null");
		}
		
		String base64Image = null;
		double[][] points = new double[17][2];

		if(picture.getImage() != null)
		base64Image = encoder.encodeToString(picture.getImage());

		points[0][0] = picture.getX1();
		points[0][1] = picture.getY1();
		points[1][0] = picture.getX2();
		points[1][1] = picture.getY2();
		points[2][0] = picture.getX3();
		points[2][1] = picture.getY3();
		points[3][0] = picture.getX4();
		points[3][1] = picture.getY4();
		points[4][0] = picture.getX5();
		points[4][1] = picture.getY5();
		points[5][0] = picture.getX6();
		points[5][1] = picture.getY6();
		points[6][0] = picture.getX7();
		points[6][1] = picture.getY7();
		points[7][0] = picture.getX8();
		points[7][1] = picture.getY8();
		points[8][0] = picture.getX9();
		points[8][1] = picture.getY9();
		points[9][0] = picture.getX10();
		points[9][1] = picture.getY10();
		points[10][0] = picture.getX11();
		points[10][1] = picture.getY11();
		points[11][0] = picture.getX12();
		points[11][1] = picture.getY12();
		points[12][0] = picture.getX13();
		points[12][1] = picture.getY13();
		points[13][0] = picture.getX14();
		points[13][1] = picture.getY14();
		points[14][0] = picture.getX15();
		points[14][1] = picture.getY15();
		points[15][0] = picture.getX16();
		points[15][1] = picture.getY16();
		points[16][0] = picture.getX17();
		points[16][1] = picture.getY17();

		return PictureDTO.builder()
				.id(picture.getId())
				.createdAt(picture.getCreatedAt())
				.location(picture.getLocation())
				.base64Image(base64Image)
				.points(points)
				.build();
	}
}
