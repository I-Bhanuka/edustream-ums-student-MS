package com.example.edustream_studentMS.controller;

import com.example.edustream_studentMS.dto.requestDTO.RegisterStudentRequestDTO;
import com.example.edustream_studentMS.dto.requestDTO.StudentRequestDTO;
import com.example.edustream_studentMS.dto.responseDTO.ApiResponse;
import com.example.edustream_studentMS.dto.responseDTO.PageResponseDTO;
import com.example.edustream_studentMS.dto.responseDTO.RegisterStudentResponseDTO;
import com.example.edustream_studentMS.entity.Student;
import com.example.edustream_studentMS.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.PageUtil;

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

    @PostMapping("/all")
    public ResponseEntity<ApiResponse<PageResponseDTO<Student>>> getAllStudents(Pageable pageable) {

        Page<Student> response = studentService.getAllStudents(pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<PageResponseDTO<Student>>builder()
                        .success(true)
                        .message("Students retrieved successfully")
                        .data(PageUtil.toPageResponse(response))
                        .build());
    }

    @PostMapping("/getStudentById")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@RequestBody StudentRequestDTO request) {

        Student response = studentService.getStudentById(request.getStudentId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<Student>builder()
                        .success(true)
                        .message("Student retrieved successfully")
                        .data(response)
                        .build());
    }


}
