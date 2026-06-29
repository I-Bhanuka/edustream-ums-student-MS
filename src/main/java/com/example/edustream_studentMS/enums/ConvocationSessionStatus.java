package com.example.edustream_studentMS.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConvocationSessionStatus {
    PENDING("PENDING", "Convocation session is pending"),
    APPROVED("APPROVED", "Convocation session is approved"),
    REJECTED("REJECTED", "Convocation session is rejected"),
    FINALIZED("FINALIZED", "Convocation session is finalized");

    private final String code;
    private final String description;
}
