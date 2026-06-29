package com.example.edustream_studentMS.service.impl;

import com.example.edustream_lib_security.util.SecurityContextUtil;
import com.example.edustream_studentMS.dto.requestDTO.ConvocationSessionRequest;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationSessionResponse;
import com.example.edustream_studentMS.entity.Convocation;
import com.example.edustream_studentMS.entity.ConvocationSession;
import com.example.edustream_studentMS.entity.ConvocationSessionStatus;
import com.example.edustream_studentMS.exception.NotFoundException;
import com.example.edustream_studentMS.repository.ConvocationRepository;
import com.example.edustream_studentMS.repository.ConvocationSessionRepository;
import com.example.edustream_studentMS.repository.ConvocationSessionStatusRepository;
import com.example.edustream_studentMS.service.ConvocationSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConvocationSessionServiceImpl implements ConvocationSessionService {

    private final ConvocationRepository convocationRepository;
    private final ConvocationSessionStatusRepository convocationSessionStatusRepository;
    private final SecurityContextUtil securityContextUtil;
    private final ConvocationSessionRepository convocationSessionRepository;

    @Override
    @Transactional
    public ConvocationSessionResponse createConvocationSession(ConvocationSessionRequest request) {
        log.info("================================ Create Convocation Session ==============================");

        log.info("Convocation session request details: {}", request.toString());

        // Instantiate a new ConvocationSession entity
        ConvocationSession convocationSession = new ConvocationSession();

        // In real application, this should be UUID but here it is String  because in my Sec context, username is stored
        // Set the updatedBy and createdBy fields with the current user's ID from the security context
        Optional<String> userId = securityContextUtil.getCurrentUserId();
        userId.ifPresent(id -> {
            convocationSession.setCreatedBy(id);
            convocationSession.setUpdatedBy(id);
        });


        // Load Convocation entity from the database using the provided convocationId
        // In the real application it should be ResourceNotFound Exception but here I am using NotFoundException because I have not created ResourceNotFoundException class
        Convocation convocation = convocationRepository.findById(request.getConvocationId())
                .orElseThrow(() -> new NotFoundException("Convocation not found: " + request.getConvocationId()));

        // Load ConvocationSessionStatus entity from the database using the provided convocationSessionStatusId
        ConvocationSessionStatus convocationSessionStatus = convocationSessionStatusRepository.findByStatus("PENDING")
                .orElseThrow(() -> new NotFoundException("Convocation Session Status not found: PENDING"));


        convocationSession.setConvocation(convocation);
        convocationSession.setStatus(convocationSessionStatus);
        convocationSession.setName(request.getSessionName());
        convocationSession.setSessionDate(request.getSessionDate());
        convocationSession.setFromTime(request.getFromTime());
        convocationSession.setToTime(request.getToTime());

        // These values are optional, so we check if they are provided in the request before setting them
        if (request.getCapacity() != null) {
            convocationSession.setCapacity(request.getCapacity());
        }
        if (request.getNoOfPasses() != null) {
            convocationSession.setNoOfPasses(request.getNoOfPasses());
        }
        if (request.getNoOfStaff() != null) {
            convocationSession.setNoOfStaff(request.getNoOfStaff());
        }
        if (request.getChiefGuest() != null) {
            convocationSession.setChiefGuest(request.getChiefGuest());
        }

        // Save the ConvocationSession entity to the database
        log.info("Saving Convocation to the database...");
        ConvocationSession savedConvocationSession = convocationSessionRepository.save(convocationSession);
        log.info("Convocation session saved: {}", savedConvocationSession.toString());

        return mapToConvocationSessionResponse(savedConvocationSession);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ConvocationSessionResponse> getConvocationSessionsByConvocationId(UUID convocationId) {
        log.info("================================ Get Convocation Sessions by Convocation ID ==============================");
        log.info("Fetching convocation sessions for convocationId: {}", convocationId);

        // Fetch all ConvocationSession entities associated with the given convocationId
        List<ConvocationSession> convocationSessions = convocationSessionRepository.findByConvocationId(convocationId);

        if (convocationSessions.isEmpty()) {
            log.warn("No Convocation Sessions found for convocationId: {}", convocationId);
            return List.of(); // Return an empty list if no records found
        }

        log.info("Found {} Convocation Sessions for convocationId: {}", convocationSessions.size(), convocationId);

        // Map each ConvocationSession entity to a ConvocationSessionResponse DTO
        return convocationSessions.stream()
                .map(this::mapToConvocationSessionResponse)
                .toList();
    }



    // ============================= Helper Methods

    // Helper method to map ConvocationSession entity to ConvocationSessionResponse DTO
    public ConvocationSessionResponse mapToConvocationSessionResponse(ConvocationSession convocationSession) {
        return ConvocationSessionResponse.builder()
                .convocationSessionId(convocationSession.getId())
                .convocationId(convocationSession.getConvocation().getId())
                .sessionName(convocationSession.getName())
                .sessionDate(convocationSession.getSessionDate())
                .fromTime(convocationSession.getFromTime())
                .toTime(convocationSession.getToTime())
                .capacity(convocationSession.getCapacity())
                .noOfPasses(convocationSession.getNoOfPasses())
                .noOfStaff(convocationSession.getNoOfStaff())
                .chiefGuest(convocationSession.getChiefGuest())
                .convocationSessionStatus(convocationSession.getStatus().getName())
                .build();

    }
}
