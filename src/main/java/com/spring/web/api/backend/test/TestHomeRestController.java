package com.spring.web.api.backend.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Tag(name = "Test API")
public class TestHomeRestController {
	@Operation(summary = "Test controller")
	@ApiResponse(responseCode = "200",
		content = {@Content(mediaType = "application/json")})
	@GetMapping("/hello-world")
	String getHelloWorld(){
		log.info("/hello-world request");
		return "Hello world!!!";
	}

	@Operation(summary = "Test controller")
	@ApiResponse(responseCode = "200",
		content = {@Content(mediaType = "application/json")})
	@GetMapping("/")
	String getDefault(){
		log.info("/ request");
		return "Default";
	}
}
