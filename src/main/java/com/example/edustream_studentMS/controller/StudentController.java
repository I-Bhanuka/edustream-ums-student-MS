package com.example.edustream_studentMS.controller;

import com.example.edustream_studentMS.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/test")
    public String testEndpoint() {

        return studentService.testService();
    }
}
