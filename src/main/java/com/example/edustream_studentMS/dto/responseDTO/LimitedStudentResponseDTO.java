package com.example.edustream_studentMS.dto.responseDTO;

import com.example.edustream_studentMS.enums.StudentStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LimitedStudentResponseDTO {
    private String studentId;

    private String firstName;

    private  String lastName;

    private String email;

    private LocalDate dob;

    private LocalDate enrollmentDate;

    private StudentStatus studentStatus;

    private UUID courseUUID;
}
