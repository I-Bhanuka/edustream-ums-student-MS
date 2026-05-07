package com.example.edustream_studentMS.service.impl;

import com.example.edustream_studentMS.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Override
    public String testService() {
        log.info("StudentServiceImpl: testEndpoint called");
        return "Hello from Student Microservice Service Layer!";
    }
}
