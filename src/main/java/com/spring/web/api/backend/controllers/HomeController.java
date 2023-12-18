package com.spring.web.api.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class HomeController {

	Logger log = Logger.getLogger(HomeController.class.getName());

	@GetMapping
	String getDefault() {
		log.info("URL: /");
		return "index.html";
	}
}
