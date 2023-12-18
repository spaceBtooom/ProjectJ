package com.spring.web.api.backend.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@Operation(summary = "Hello world message")
	@GetMapping("/hello-world")
	String getHelloWorld(){
		return "Hello world!!!";
	}

	@ApiResponse(responseCode = "200",
		content = {@Content(mediaType = "application/json")})
	@GetMapping("/")
	String getDefault(){
		return "Default";
	}
}
