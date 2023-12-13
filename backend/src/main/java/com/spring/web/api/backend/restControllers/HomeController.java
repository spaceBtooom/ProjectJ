package com.spring.web.api.backend.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    String getDefault(){
        return "Default message";
    }
    @GetMapping("/home")
    String getHome(){
        return "Welcome from the server";
    }
}
