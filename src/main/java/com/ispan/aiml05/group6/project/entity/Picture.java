package com.ispan.aiml05.group6.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PICTURE")
public class Picture {
	
	@Id
	@Column(name = "PIC_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "PIC_CREATED_AT")
	private LocalDateTime createdAt;
	
	@Column(name = "PIC_LOC")
	private String location;
	
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private double x3;
	private double y3;
	private double x4;
	private double y4;
	private double x5;
	private double y5;
	private double x6;
	private double y6;
	private double x7;
	private double y7;
	private double x8;
	private double y8;
	private double x9;
	private double y9;
	private double x10;
	private double y10;
	private double x11;
	private double y11;
	private double x12;
	private double y12;
	private double x13;
	private double y13;
	private double x14;
	private double y14;
	private double x15;
	private double y15;
	private double x16;
	private double y16;
	private double x17;
	private double y17;
}
