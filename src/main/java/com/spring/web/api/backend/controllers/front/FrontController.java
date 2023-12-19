package com.spring.web.api.backend.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class FrontController {

	Logger log = Logger.getLogger(FrontController.class.getName());

	@GetMapping
	String getDefault() {
		log.info("URL: /");
		return "index.html";
	}
}
