package com.example.edustream_studentMS.dto.requestDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ManageConvocationRequest {

    private String convocationName;

    private Short convocationYear;

    private String convocationStatus;
}
