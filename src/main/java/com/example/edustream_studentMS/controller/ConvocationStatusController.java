package com.example.edustream_studentMS.controller;

import com.example.edustream_lib_common.responseDTO.ApiResponse;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationStatusResponse;
import com.example.edustream_studentMS.service.ConvocationStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/convocation-status")
@Tag(name = "Convocation Status Controller", description = "Controller for managing convocation status")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ConvocationStatusController {

    private final ConvocationStatusService convocationStatusService;


    @GetMapping
    @Operation(summary = "Get all convocation status", description = "Retrieves all convocation status")
    public ResponseEntity<ApiResponse<List<ConvocationStatusResponse>>> getAllConvocationStatus() {

        List<ConvocationStatusResponse> response = convocationStatusService.getAllConvocationStatus();

        return ResponseEntity.ok(ApiResponse.success(response, "Convocation status retrieved successfully"));

    }
}
