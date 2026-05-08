package com.example.edustream_studentMS.service.impl;

import com.example.edustream_studentMS.dto.requestDTO.RegisterStudentRequestDTO;
import com.example.edustream_studentMS.dto.responseDTO.RegisterStudentResponseDTO;
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

    @Override
    public RegisterStudentResponseDTO registerStudent(RegisterStudentRequestDTO registerStudentRequestDTO) {
        log.info("StudentServiceImpl: createStudent called");

        log.info("Student Details - Name: {}, Email: {}, DOB: {}",
                 registerStudentRequestDTO.getFirstName() + " " + registerStudentRequestDTO.getLastName(),
                 registerStudentRequestDTO.getEmail(),
                 registerStudentRequestDTO.getDob());

        return RegisterStudentResponseDTO.builder()
                .studentId("20230000")
                .firstName(registerStudentRequestDTO.getFirstName())
                .lastName(registerStudentRequestDTO.getLastName())
                .dob(registerStudentRequestDTO.getDob())
                .email(registerStudentRequestDTO.getEmail())
                .build();
    }
}
