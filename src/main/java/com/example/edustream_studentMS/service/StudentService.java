package com.example.edustream_studentMS.service;

import com.example.edustream_studentMS.dto.requestDTO.RegisterStudentRequestDTO;
import com.example.edustream_studentMS.dto.responseDTO.RegisterStudentResponseDTO;

public interface StudentService {
    String testService();

    RegisterStudentResponseDTO registerStudent(RegisterStudentRequestDTO registerStudentRequestDTO);

    String getAllStudents();
}
