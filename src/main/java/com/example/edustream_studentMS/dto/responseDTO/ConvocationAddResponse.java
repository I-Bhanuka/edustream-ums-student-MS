package com.example.edustream_studentMS.dto.responseDTO;

import com.example.edustream_studentMS.enums.ConvocationStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ConvocationAddResponse {

    private UUID convocationId;

    private String convocationName;

    private short convocationYear;

    private String convocationStatus;

    private BigDecimal convocationPayment;

    private LocalDate supplicantOpenDate;

    private LocalDate supplicantEndDate;
}
