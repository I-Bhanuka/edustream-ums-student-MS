package com.example.edustream_studentMS.service.impl;

import com.example.edustream_studentMS.dto.requestDTO.ConvocationRequest;
import com.example.edustream_studentMS.dto.requestDTO.ManageConvocationRequest;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationAddResponse;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationResponse;
import com.example.edustream_studentMS.dto.responseDTO.ManageConvocationResponse;
import com.example.edustream_studentMS.entity.Convocation;
import com.example.edustream_studentMS.entity.ConvocationStatus;
import com.example.edustream_studentMS.repository.ConvocationRepository;
import com.example.edustream_studentMS.repository.ConvocationStatusRepository;
import com.example.edustream_studentMS.service.ConvocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.edustream_lib_security.util.SecurityContextUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Implementation of the ConvocationService interface.
 * ==============================
 * Available service methods,
 * 1. createConvocation: Creates a new convocation based on the provided request data.
 * 2. getAllConvocations: Retrieves a list of all convocations.
 * 3. searchConvocations: Searches for convocations based on the provided criteria.
 * 4. getConvocationById: Retrieves a convocation by its unique identifier.
 * 5. getAllConvocationNames: Retrieves a list of all convocation names.
 * 6. getConvocationNames: Retrieves a list of convocation names matching the provided name.
 * ==============================
 * Available helper methods,
 * 1. mapToConvocationAddResponse: Maps a Convocation entity to a ConvocationAddResponse DTO.
 * 2. mapToConvocationResponse: Maps a Convocation entity to a ConvocationResponse DTO.
 * 3. mapToManageConvocationResponse: Maps a Convocation entity to a ManageConvocationResponse DTO.
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class ConvocationServiceImpl implements ConvocationService {

    private final ConvocationRepository convocationRepository;
    private final ConvocationStatusRepository convocationStatusRepository;
    private final SecurityContextUtil securityContextUtil;

    @Override
    @Transactional
    public ConvocationAddResponse createConvocation(ConvocationRequest convocationRequest) {
        log.info("================================ Create New Convocation ==============================");

        log.info("Convocation create Request details - Convocation Name: {}, Year: {}, Convocation Payment: {}, Supplicant Open Date: {}, Supplicant End Date: {}",
                convocationRequest.getConvocationName(),
                convocationRequest.getConvocationYear(),
                convocationRequest.getConvocationPayment(),
                convocationRequest.getSupplicantOpenDate(),
                convocationRequest.getSupplicantEndDate());

        // Instantiate a new Convocation entity
        Convocation convocation = new Convocation();

        // In real application, this should be UUID but here it is String  because in my Sec context, username is stored
        // Set the updatedBy and createdBy fields with the current user's ID from the security context
        Optional<String> userId = securityContextUtil.getCurrentUserId();
        userId.ifPresent(id -> {
            convocation.setCreatedBy(id);
            convocation.setUpdatedBy(id);
        });

        // Retrieve the ID for the NOT HELD status from the ConvocationStatusRepository
        ConvocationStatus convocationStatusId = convocationStatusRepository.findByStatus("NOT_HELD")
                .orElseThrow(() -> new RuntimeException("Convocation Status 'NOT HELD' not found"));

        // Set the ConvocationStatusId in the Convocation entity
        convocation.setStatus(convocationStatusId);

        // Set the remaining fields in the Convocation entity using the data from the ConvocationRequest
        convocation.setName(convocationRequest.getConvocationName());
        convocation.setYear(convocationRequest.getConvocationYear());
        convocation.setPayment(convocationRequest.getConvocationPayment());
        convocation.setSupplementOpenDate(convocationRequest.getSupplicantOpenDate());
        convocation.setSupplementEndDate(convocationRequest.getSupplicantEndDate());
        // convocation.setDeleted(false);

        // Save to the database
        log.info("Saving Convocation to the database...");
        Convocation dbResponse = convocationRepository.save(convocation);
        log.info("Convocation Save Response: {}", dbResponse);


        return mapToConvocationAddResponse(dbResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConvocationResponse> getAllConvocations() {
        log.info("================================ Get All Convocations ==============================");
//        List<Convocation> convocations = convocationRepository.findAllByDeletedFalse();
        List<Convocation> convocations = convocationRepository.findAll();
        log.info("Total Convocations retrieved: {}", convocations.size());
        return convocations.stream()
                .map(this::mapToConvocationResponse)
                .toList();
    }


    @Transactional(readOnly = true)
    @Override
    public List<ManageConvocationResponse> searchConvocations(ManageConvocationRequest request) {
        log.info("================================ Search Convocations ==============================");
        log.info("Search Convocation Request details - Convocation Name: {}, Year: {}, Status: {}",
                request.getConvocationName(),
                request.getConvocationYear(),
                request.getConvocationStatus());

        List<Convocation> convocations = convocationRepository.searchConvocations(
                request.getConvocationName(),
                request.getConvocationYear(),
                request.getConvocationStatus()
        );

        log.info("Total Convocations found: {}", convocations.size());

        return convocations.stream()
                .map(this::mapToManageConvocationResponse)
                .toList();
    }


    @Transactional(readOnly = true)
    @Override
    public ConvocationResponse getConvocationById(UUID id) {
        log.info("================================ Get Convocation By ID ==============================");
        log.info("Get Convocation by ID: {}", id);

        Convocation convocation = convocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convocation not found with ID: " + id));

        log.info("Convocation retrieved: {}", convocation);

        return mapToConvocationResponse(convocation);
    }


    @Transactional(readOnly = true)
    @Override
    public List<String> getAllConvocationNames() {
        log.info("================================ Get All Convocation Names ==============================");
        List<String> convocationNames = convocationRepository.findNames(null);
        log.info("Total Convocation Names retrieved: {}", convocationNames.size());
        return convocationNames;
    }


    @Transactional(readOnly = true)
    @Override
    public List<String> getConvocationNames(String name) {
        log.info("================================ Get Convocation Names matching = \"{}\" ==============================", name);
        List<String> convocationNames = convocationRepository.findNames(name);
        log.info("Total Convocation Names retrieved for name '{}': {}", name, convocationNames.size());
        return convocationNames;
    }





    // ============================= Helper Methods

    // For mapping Convocation entity to ConvocationAddResponse DTO
    public ConvocationAddResponse mapToConvocationAddResponse(Convocation convocation) {
        return ConvocationAddResponse.builder()
                .convocationId(convocation.getId())
                .convocationName(convocation.getName())
                .convocationYear(convocation.getYear())
                .convocationStatus(convocation.getStatus().getStatus())
                .convocationPayment(convocation.getPayment())
                .supplicantOpenDate(convocation.getSupplementOpenDate())
                .supplicantEndDate(convocation.getSupplementEndDate())
                .build();
    }

    // For mapping Convocation entity to ConvocationBasicResponse DTO
    public ConvocationResponse mapToConvocationResponse(Convocation convocation) {
        return ConvocationResponse.builder()
                .convocationId(convocation.getId())
                .convocationName(convocation.getName())
                .convocationYear(convocation.getYear())
                .convocationStatus(convocation.getStatus().getStatus())
                .convocationPayment(convocation.getPayment())
                .supplicantOpenDate(convocation.getSupplementOpenDate())
                .supplicantEndDate(convocation.getSupplementEndDate())
                .build();
    }

    public ManageConvocationResponse mapToManageConvocationResponse(Convocation convocation) {
        return ManageConvocationResponse.builder()
                .convocationId(convocation.getId())
                .convocationName(convocation.getName())
                .convocationYear(convocation.getYear())
                .convocationStatus(convocation.getStatus().getStatus())
                .build();
    }


}
