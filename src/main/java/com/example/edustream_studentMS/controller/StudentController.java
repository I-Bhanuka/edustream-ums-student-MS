package com.example.edustream_studentMS.controller;

import com.example.edustream_studentMS.dto.requestDTO.RegisterStudentRequestDTO;
import com.example.edustream_studentMS.dto.responseDTO.ApiResponse;
import com.example.edustream_studentMS.dto.responseDTO.RegisterStudentResponseDTO;
import com.example.edustream_studentMS.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/test")
    public String testEndpoint() {

        return studentService.testService();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<RegisterStudentResponseDTO>> registerStudent(
            @Valid @RequestBody RegisterStudentRequestDTO registerStudentRequestDTO) {

        RegisterStudentResponseDTO response = studentService.registerStudent(registerStudentRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.<RegisterStudentResponseDTO>builder()
                        .success(true)
                        .message("Student registered successfully")
                        .data(response)
                        .build());
    }


}
