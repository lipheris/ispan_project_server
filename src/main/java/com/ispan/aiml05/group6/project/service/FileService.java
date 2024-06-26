package com.ispan.aiml05.group6.project.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class FileService {
    public void createDirectories(Path path) throws IOException {
        Files.createDirectories(path);
    }
	public void write(Path path, byte[] bytes) throws IOException {
        Files.write(path, bytes);
    }
}
