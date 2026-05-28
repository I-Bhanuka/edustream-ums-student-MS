package com.example.edustream_studentMS.controller;

import com.example.edustream_studentMS.dto.requestDTO.RegisterStudentRequestDTO;
import com.example.edustream_studentMS.dto.requestDTO.RegisterStudentToCourseRequestDTO;
import com.example.edustream_studentMS.dto.requestDTO.StudentRequestDTO;
import com.example.edustream_lib_common.responseDTO.ApiResponse;
import com.example.edustream_studentMS.dto.responseDTO.LimitedStudentResponseDTO;
import com.example.edustream_studentMS.dto.responseDTO.PageResponseDTO;
import com.example.edustream_studentMS.dto.responseDTO.RegisterStudentResponseDTO;
import com.example.edustream_studentMS.entity.Student;
import com.example.edustream_studentMS.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Student Controller", description = "Endpoints for managing students")
@SecurityRequirement(name = "bearerAuth")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/test")
    @Operation(summary = "Test Endpoint", description = "A simple endpoint to test if the service is running")
    public String testEndpoint() {

        return studentService.testService();
    }

    @PostMapping("/create")
    @Operation(summary = "Register Student", description = "Endpoint to register a new student")
    public ResponseEntity<ApiResponse<RegisterStudentResponseDTO>> registerStudent(
            @Valid @RequestBody RegisterStudentRequestDTO registerStudentRequestDTO) {

        RegisterStudentResponseDTO response = studentService.registerStudent(registerStudentRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Student registered successfully"));

    }

    @PostMapping("/all")
    @Operation(summary = "Get All Students with All Details", description = "Endpoint to retrieve all students with all their details")
    public ResponseEntity<ApiResponse<PageResponseDTO<Student>>> getAllStudentsWithAllDetails(Pageable pageable) {

        Page<Student> response = studentService.getAllStudentsWithAllDetails(pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(PageUtil.toPageResponse(response), "Students retrieved successfully"));
    }

    @PostMapping("/allWithLimitedDetails")
    @Operation(summary = "Get All Students with Limited Details", description = "Endpoint to retrieve all students with limited details (id, name, email)")
    public ResponseEntity<ApiResponse<PageResponseDTO<LimitedStudentResponseDTO>>> getAllStudentsWithAllWithLimitedDetails(Pageable pageable) {

        Page<LimitedStudentResponseDTO> response = studentService.getAllStudentsWithLimitedDetails(pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(PageUtil.toPageResponse(response), "Students retrieved successfully"));

    }



    @PostMapping("/getStudentById")
    @Operation(summary = "Get Student by ID", description = "Endpoint to retrieve a student by their ID")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@RequestBody StudentRequestDTO request) {

        Student response = studentService.getStudentById(request.getStudentId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(response, "Student retrieved successfully"));

    }

    @PostMapping("/registerToCourse")
    @Operation(summary = "Register Student to Course", description = "Endpoint to register a student to a course")
    public ResponseEntity<ApiResponse<String>> registerStudentToCourse(
            @RequestBody RegisterStudentToCourseRequestDTO request) {

        String response = studentService.registerStudentToCourse(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(response, "Student registered to course successfully"));

    }


}
