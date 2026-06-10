package com.example.edustream_studentMS.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConvocationStatus {
    HELD("HELD", "Convocation has held"),
    NOT_HELD("NOT_HELD", "Convocation has not held");

    private final String code;
    private final String description;
}
