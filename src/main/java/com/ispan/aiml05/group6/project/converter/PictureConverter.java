package com.ispan.aiml05.group6.project.converter;

import com.ispan.aiml05.group6.project.dto.PictureDTO;
import com.ispan.aiml05.group6.project.entity.Picture;

public class PictureConverter {

	public static Picture convert(PictureDTO pictureDto) {
		return Picture.builder()
				.id(pictureDto.getId())
				.createdAt(pictureDto.getCreatedAt())
				.location(pictureDto.getLocation())
				.x1(pictureDto.getPoints()[0][0])
				.y1(pictureDto.getPoints()[0][1])
				.x2(pictureDto.getPoints()[1][0])
				.y2(pictureDto.getPoints()[1][1])
				.x3(pictureDto.getPoints()[2][0])
				.y3(pictureDto.getPoints()[2][1])
				.x4(pictureDto.getPoints()[3][0])
				.y4(pictureDto.getPoints()[3][1])
				.x5(pictureDto.getPoints()[4][0])
				.y5(pictureDto.getPoints()[4][1])
				.x6(pictureDto.getPoints()[5][0])
				.y6(pictureDto.getPoints()[5][1])
				.x7(pictureDto.getPoints()[6][0])
				.y7(pictureDto.getPoints()[6][1])
				.x8(pictureDto.getPoints()[7][0])
				.y8(pictureDto.getPoints()[7][1])
				.x9(pictureDto.getPoints()[8][0])
				.y9(pictureDto.getPoints()[8][1])
				.x10(pictureDto.getPoints()[9][0])
				.y10(pictureDto.getPoints()[9][1])
				.x11(pictureDto.getPoints()[10][0])
				.y11(pictureDto.getPoints()[10][1])
				.x12(pictureDto.getPoints()[11][0])
				.y12(pictureDto.getPoints()[11][1])
				.x13(pictureDto.getPoints()[12][0])
				.y13(pictureDto.getPoints()[12][1])
				.x14(pictureDto.getPoints()[13][0])
				.y14(pictureDto.getPoints()[13][1])
				.x15(pictureDto.getPoints()[14][0])
				.y15(pictureDto.getPoints()[14][1])
				.x16(pictureDto.getPoints()[15][0])
				.y16(pictureDto.getPoints()[15][1])
				.x17(pictureDto.getPoints()[16][0])
				.y17(pictureDto.getPoints()[16][1])
				.build();
	}

	public static PictureDTO convert(Picture picture) {
		double[][] points = new double[17][2];
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
				.points(points)
				.build();
	}
}
