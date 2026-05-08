package com.example.edustream_studentMS.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentRequestDTO {

    @NotBlank(message = "Student ID is required")
    private String studentId;
}
