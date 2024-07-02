package com.ispan.aiml05.group6.project;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMain() {
		// Mock static methods
		try (MockedStatic<SpringApplication> springApplicationMockedStatic = mockStatic(SpringApplication.class);
				MockedStatic<TimeZone> timeZoneMockedStatic = mockStatic(TimeZone.class)) {

			String[] args = {};
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Taipei");

			// Mock TimeZone.getTimeZone
			timeZoneMockedStatic.when(() -> TimeZone.getTimeZone("Asia/Taipei")).thenReturn(timeZone);

			// Call the main method
			ProjectApplication.main(args);

			// Verify TimeZone.setDefault is called with the correct time zone
			timeZoneMockedStatic.verify(() -> TimeZone.setDefault(timeZone), times(1));

			// Verify SpringApplication.run is called with the correct arguments
			springApplicationMockedStatic.verify(() -> SpringApplication.run(ProjectApplication.class, args), times(1));
		}
	}
}
