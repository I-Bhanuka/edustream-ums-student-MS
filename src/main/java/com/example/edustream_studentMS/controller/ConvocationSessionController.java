package com.example.edustream_studentMS.controller;

import com.example.edustream_lib_common.responseDTO.ApiResponse;
import com.example.edustream_studentMS.dto.requestDTO.ConvocationSessionApproveRequest;
import com.example.edustream_studentMS.dto.requestDTO.ConvocationSessionRejectRequest;
import com.example.edustream_studentMS.dto.requestDTO.ConvocationSessionRequest;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationSessionResponse;
import com.example.edustream_studentMS.service.ConvocationSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/convocation-session")
@RequiredArgsConstructor
@Tag(name = "Convocation Controller", description = "Endpoints for managing convocations")
@SecurityRequirement(name = "bearerAuth")
public class ConvocationSessionController {

    private  final ConvocationSessionService convocationSessionService;

    @PostMapping("/create")
    @Operation(summary = "Create Convocation Session", description = "Creates a new convocation session")
    public ResponseEntity<ApiResponse<ConvocationSessionResponse>> createConvocationSession(
            @Valid @RequestBody ConvocationSessionRequest request) {

        log.info("Create new convocation session: {}", request.getSessionName());

        ConvocationSessionResponse response = convocationSessionService.createConvocationSession(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(response, "Convocation session created successfully"));
    }


    @GetMapping("/convocation-sesssions/{convocationId}")
    @Operation(summary = "Get Convocation Sessions by Convocation ID", description = "Retrieves convocation sessions by convocation ID")
    public  ResponseEntity<ApiResponse<List<ConvocationSessionResponse>>> getConvocationSessionsByConvocationId(
            @PathVariable UUID convocationId) {

        log.info("Get convocation sessions for convocation ID: {}", convocationId);

        List<ConvocationSessionResponse> responseList = convocationSessionService.getConvocationSessionsByConvocationId(convocationId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(responseList, "Convocation sessions retrieved successfully"));
    }


    @PostMapping("/approve/{sessionId}")
    @Operation(summary = "Approve Convocation Session", description = "Approves a convocation session by its ID")
    public ResponseEntity<ApiResponse<ConvocationSessionResponse>> approveConvocationSession(
            @PathVariable UUID sessionId, @Valid @RequestBody ConvocationSessionApproveRequest request) {

        log.info("Approve convocation session with ID: {}", sessionId);

        ConvocationSessionResponse response = convocationSessionService.approveConvocationSession(sessionId, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(response, "Convocation session approved successfully"));
    }

    @PostMapping("/reject/{sessionId}")
    @Operation(summary = "Reject Convocation Session", description = "Rejects a convocation session by its ID")
    public ResponseEntity<ApiResponse<ConvocationSessionResponse>> rejectConvocationSession(
            @PathVariable UUID sessionId, @Valid @RequestBody ConvocationSessionRejectRequest request) {

        log.info("Reject convocation session with ID: {}", sessionId);

        ConvocationSessionResponse response = convocationSessionService.rejectConvocationSession(sessionId, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(response, "Convocation session rejected successfully"));
    }
}
