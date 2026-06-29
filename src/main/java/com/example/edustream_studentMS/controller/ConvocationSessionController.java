package com.example.edustream_studentMS.controller;

import com.example.edustream_lib_common.responseDTO.ApiResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
