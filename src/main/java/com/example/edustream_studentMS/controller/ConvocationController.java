package com.example.edustream_studentMS.controller;

import com.example.edustream_lib_common.responseDTO.ApiResponse;
import com.example.edustream_studentMS.dto.requestDTO.ConvocationRequest;
import com.example.edustream_studentMS.dto.requestDTO.ManageConvocationRequest;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationAddResponse;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationResponse;
import com.example.edustream_studentMS.dto.responseDTO.ManageConvocationResponse;
import com.example.edustream_studentMS.service.ConvocationService;
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

@Slf4j
@RestController
@RequestMapping("api/convocation")
@RequiredArgsConstructor
@Tag(name = "Convocation Controller", description = "Endpoints for managing convocations")
@SecurityRequirement(name = "bearerAuth")
public class ConvocationController {

    private final ConvocationService convocationService;

    @PostMapping("/create")
    @Operation(summary = "Create Convocation", description = "Endpoint to create a new convocation")
    public ResponseEntity<ApiResponse<ConvocationAddResponse>> create(
            @Valid @RequestBody ConvocationRequest convocationRequest) {

        ConvocationAddResponse response = convocationService.createConvocation(convocationRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Convocation created successfully"));

    }

    @GetMapping
    @Operation(summary = "Get All Convocations", description = "Endpoint to retrieve all convocations")
    public ResponseEntity<ApiResponse<List<ConvocationResponse>>> getAllConvocations() {

        List<ConvocationResponse> response = convocationService.getAllConvocations();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(response, "Convocations retrieved successfully"));
    }


    @PostMapping("/search")
    @Operation(summary = "Search Convocations to manage convocations", description = "Endpoint to search convocations based on criteria")
    public ResponseEntity<ApiResponse<List<ManageConvocationResponse>>> searchConvocations(
            @Valid @RequestBody ManageConvocationRequest request) {

        List<ManageConvocationResponse> response = convocationService.searchConvocations(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(response, "Convocations searched successfully"));
    }
}
