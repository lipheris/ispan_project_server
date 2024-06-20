package com.ispan.aiml05.group6.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("/camera")
	public String camera(){
		return "camera.html";
	}
}
