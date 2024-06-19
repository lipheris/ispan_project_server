package com.ispan.aiml05.group6.project.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.aiml05.group6.project.entity.Picture;

public interface PictureRepo extends JpaRepository<Picture, Long> {
	@Query("select p from Picture p " +
			"where (p.createdAt BETWEEN :startDate AND :endDate) AND p.location = :location")
	List<Picture> findPicturesByCreatedAtRangeAndLocation(
			@Param("startDate") LocalDateTime startDate,
			@Param("endDate") LocalDateTime endDate,
			@Param("location") String location);
}
