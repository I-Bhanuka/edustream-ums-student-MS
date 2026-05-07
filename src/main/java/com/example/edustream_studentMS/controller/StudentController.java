package com.example.edustream_studentMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class StudentController {

    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello from Student Microservice!";
    }
}
