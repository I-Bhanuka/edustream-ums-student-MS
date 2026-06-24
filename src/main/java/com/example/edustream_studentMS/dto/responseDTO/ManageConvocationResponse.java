package com.example.edustream_studentMS.dto.responseDTO;

import com.example.edustream_studentMS.enums.ConvocationStatus;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ManageConvocationResponse {

    private UUID convocationId;

    private String convocationName;

    private short convocationYear;

    private String convocationStatus;

}
