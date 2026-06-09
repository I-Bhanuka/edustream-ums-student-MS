package com.example.edustream_studentMS.service.impl;

import com.example.edustream_studentMS.dto.requestDTO.ConvocationRequest;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationAddResponse;
import com.example.edustream_studentMS.entity.Convocation;
import com.example.edustream_studentMS.repository.ConvocationRepository;
import com.example.edustream_studentMS.service.ConvocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConvocationServiceImpl implements ConvocationService {

    private final ConvocationRepository convocationRepository;

    @Override
    public ConvocationAddResponse createConvocation(ConvocationRequest convocationRequest) {
        log.info("================================ Create New Convocation ==============================");

        log.info("Convocation create Request details - Convocation Name: {}, Year: {}, Convocation Payment: {}, Supplicant Open Date: {}, Supplicant End Date: {}",
                convocationRequest.getConvocationName(),
                convocationRequest.getConvocationYear(),
                convocationRequest.getConvocationPayment(),
                convocationRequest.getSupplicantOpenDate(),
                convocationRequest.getSupplicantEndDate());

        // Construct the Convocation entity from the request DTO
        Convocation convocation = Convocation.builder()
                .convocationName(convocationRequest.getConvocationName())
                .convocationYear(convocationRequest.getConvocationYear())
                .convocationPayment(convocationRequest.getConvocationPayment())
                .supplicantOpenDate(convocationRequest.getSupplicantOpenDate())
                .supplicantEndDate(convocationRequest.getSupplicantEndDate())
                .build();

        // Save to the database
        log.info("Saving Convocation to the database...");
        Convocation dbResponse = convocationRepository.save(convocation);
        log.info("Convocation Save Response: {}", dbResponse);


        return mapToConvocationAddResponse(dbResponse);
    }



    // Helper Methods
    public ConvocationAddResponse mapToConvocationAddResponse(Convocation convocation) {
        return ConvocationAddResponse.builder()
                .convocationId(convocation.getId())
                .convocationName(convocation.getConvocationName())
                .convocationYear(convocation.getConvocationYear())
                .convocationPayment(convocation.getConvocationPayment())
                .supplicantOpenDate(convocation.getSupplicantOpenDate())
                .supplicantEndDate(convocation.getSupplicantEndDate())
                .build();
    }


}
