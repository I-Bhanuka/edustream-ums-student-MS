package com.example.edustream_studentMS.dto.responseDTO;

import com.example.edustream_studentMS.entity.Convocation;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ConvocationSessionResponse {

    private UUID convocationSessionId;

    private UUID convocationId;

    private String sessionName;

    private LocalDate sessionDate;

    private LocalDateTime fromTime;

    private LocalDateTime toTime;

    private Short capacity;

    private Short noOfPasses;

    private Short noOfStaff;

    private String chiefGuest;

    private String convocationSessionStatus;


}
