package com.example.edustream_studentMS.dto.responseDTO;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ConvocationStatusResponse {

    private UUID id;

    private String status;

    private String description;
}
