package com.example.edustream_studentMS.dto.responseDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterStudentResponseDTO {

    private String studentId;

    private String firstName;

    private String lastName;

    private String dob;

    private String email;
}
