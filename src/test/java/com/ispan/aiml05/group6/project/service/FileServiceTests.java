package com.ispan.aiml05.group6.project.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

public class FileServiceTests {
	@TempDir
	private Path tempDir;
	@Mock
	private Files files;
	@InjectMocks
	private FileService fileService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createDirectories_whenPathDoesNotExist_shouldCreateDirectories() throws IOException {

		Path newDir = tempDir.resolve("newDir");

		assertDoesNotThrow(() -> fileService.createDirectories(newDir));
		assertTrue(Files.exists(newDir));
	}

	@Test
	void createDirectories_whenPathAlreadyExists_shouldNotThrowException() throws IOException {

		assertDoesNotThrow(() -> fileService.createDirectories(tempDir));
	}

	@Test
	void createDirectories_whenPathIsFile_shouldThrowIOException() throws IOException {

		Path newFile = tempDir.resolve("newFile.txt");
		Files.createFile(newFile);

		assertThrows(IOException.class, () -> fileService.createDirectories(newFile));
	}

	@Test
	void createDirectories_whenPathIsNull_shouldThrowNullPointerException() {
		assertThrows(NullPointerException.class, () -> fileService.createDirectories(null));
	}

	@Test
	public void write_emptyArray_writesCorrectly() throws IOException {
		// Arrange
		Path filePath = Paths.get(tempDir.toString(), "test.txt");
		byte[] bytes = new byte[0];

		// Act
		fileService.write(filePath, bytes);

		// Assert
		byte[] writtenBytes = Files.readAllBytes(filePath);
		assertArrayEquals(bytes, writtenBytes);
	}

	@Test
	public void write_nullPath_throwsNullPointerException() {
		// Arrange
		byte[] bytes = new byte[0];

		// Act & Assert
		assertThrows(NullPointerException.class, () -> fileService.write(null, bytes));
	}

	@Test
	public void write_nullBytes_throwsNullPointerException() throws IOException {
		// Arrange
		Path filePath = Paths.get(tempDir.toString(), "test.txt");

		// Act & Assert
		assertThrows(NullPointerException.class, () -> fileService.write(filePath, null));
	}

	@Test
	public void write_nonExistingDirectory_createsDirectoryAndWrites() throws IOException {
		// Arrange
		Path path = Paths.get("nonExistingDir");
		FileService fileService = new FileService();

		// Mock static method Files.createDirectories
		try (MockedStatic<Files> filesMockedStatic = mockStatic(Files.class)) {
			// Act
			fileService.createDirectories(path);

			// Assert
			filesMockedStatic.verify(() -> Files.createDirectories(path), times(1));
		}
	}

	@Test
	public void write_existingFile_overwritesFile() throws IOException {
		// Arrange
		Path filePath = Paths.get(tempDir.toString(), "test.txt");
		byte[] bytes = new byte[0];

		// Create a file initially
		Files.write(filePath, bytes);

		// Act
		fileService.write(filePath, bytes);

		// Assert
		byte[] writtenBytes = Files.readAllBytes(filePath);
		assertArrayEquals(bytes, writtenBytes);
	}
}
