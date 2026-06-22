package com.example.edustream_studentMS.dto.responseDTO;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ConvocationBasicResponse {

    private UUID convocationId;

    private String convocationName;

    private int convocationYear;

    private double convocationPayment;

    private LocalDate supplicantOpenDate;

    private LocalDate supplicantEndDate;
}
