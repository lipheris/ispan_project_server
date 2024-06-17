package com.ispan.aiml05.group6.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO {
	private long id;
	private LocalDateTime createdAt;
	private String location;
	@Builder.Default
	private double[][] points = new double[17][2];
}
