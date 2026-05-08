package com.example.edustream_studentMS.service;

import com.example.edustream_studentMS.dto.requestDTO.RegisterStudentRequestDTO;
import com.example.edustream_studentMS.dto.responseDTO.RegisterStudentResponseDTO;
import com.example.edustream_studentMS.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    String testService();

    RegisterStudentResponseDTO registerStudent(RegisterStudentRequestDTO registerStudentRequestDTO);

    Page<Student> getAllStudents(Pageable pageable);

    Student getStudentById(String studentId);
}
