package com.example.edustream_studentMS.service.impl;

import com.example.edustream_studentMS.dto.requestDTO.RegisterStudentRequestDTO;
import com.example.edustream_studentMS.dto.responseDTO.RegisterStudentResponseDTO;
import com.example.edustream_studentMS.entity.Student;
import com.example.edustream_studentMS.enums.StudentStatus;
import com.example.edustream_studentMS.repository.StudentRepository;
import com.example.edustream_studentMS.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public String testService() {
        log.info("StudentServiceImpl: testEndpoint called");
        return "Hello from Student Microservice Service Layer!";
    }

    @Override
    public RegisterStudentResponseDTO registerStudent(RegisterStudentRequestDTO registerStudentRequestDTO) {
        log.info("================================ Registering New Student ==============================");

        log.info("Register Request details - First Name: {}, Last Name: {}, Email: {}, DOB: {}",
                 registerStudentRequestDTO.getFirstName(),
                 registerStudentRequestDTO.getLastName(),
                 registerStudentRequestDTO.getEmail(),
                 registerStudentRequestDTO.getDob());

        // Create a new Student entity with the requested data
        Student registerStudent = Student.builder()
                .studentId(generateStudentId())
                .firstName(registerStudentRequestDTO.getFirstName())
                .lastName(registerStudentRequestDTO.getLastName())
                .email(registerStudentRequestDTO.getEmail())
                .dob(LocalDate.parse(registerStudentRequestDTO.getDob()))
                .enrollmentDate(LocalDate.now())
                .studentStatus(StudentStatus.ACTIVE)
                .build();

        // Save the new student to the database
        log.info("Saving new student to database with studentId: {}", registerStudent.getStudentId());
        studentRepository.save(registerStudent);

        return RegisterStudentResponseDTO.builder()
                .studentId(registerStudent.getStudentId())
                .firstName(registerStudent.getFirstName())
                .lastName(registerStudent.getLastName())
                .dob(registerStudent.getDob().toString())
                .email(registerStudent.getEmail())
                .build();
    }

    /**
     *  ================================= Helper Methods =================================
     */

    // Helper method to generate studentId
    public String generateStudentId() {
        String studentId;

        // Retrieve the last studentId from database, and if there is no student in database
        Student std = studentRepository.findTopByOrderByCreatedAtDesc().orElse(null);

        if (std == null) {
            // if there is no student in database, create the first studentId with the format "ST-YYYY0000"
            log.info("Student table is empty. Generating first student ID for the year {}.", LocalDate.now().getYear());
            studentId = String.format("%s-%d%04d", "ST", LocalDate.now().getYear(), 0);
            return studentId;

        }

        // Split everything
        studentId = std.getStudentId();
        String[] parts = studentId.split("-");
        String prefix = parts[0]; // "ST"
        int yearId = Integer.parseInt(parts[1].subSequence(0, 4).toString()); // "2026"

        // Get the sub string, parse into int, and then add 1 to it
        int numberId = Integer.parseInt(parts[1].substring(4)) + 1;

        // Check the year portion of the ID
        if (yearId != LocalDate.now().getYear()) {
            // If the year portion does not match with the current year, update the year
            log.info("Resetting student ID sequence for new year. Previous year: {}, Current year: {}.", yearId, LocalDate.now().getYear());

            yearId = LocalDate.now().getYear();

            // Reset the number portion to 0000
            numberId = 0;
        }

        // Construct the new studentId
        studentId = String.format("%s-%d%04d", prefix, yearId, numberId); // "ST-20260001";
        return studentId;
    }
}
