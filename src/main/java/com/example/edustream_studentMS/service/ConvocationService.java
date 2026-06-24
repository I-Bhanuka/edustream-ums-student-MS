package com.example.edustream_studentMS.service;

import com.example.edustream_studentMS.dto.requestDTO.ConvocationRequest;
import com.example.edustream_studentMS.dto.requestDTO.ManageConvocationRequest;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationAddResponse;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationResponse;
import com.example.edustream_studentMS.dto.responseDTO.ManageConvocationResponse;

import java.util.List;
import java.util.UUID;

public interface ConvocationService {

    ConvocationAddResponse createConvocation(ConvocationRequest convocationRequest);

    List<ConvocationResponse> getAllConvocations();

    List<ManageConvocationResponse> searchConvocations(ManageConvocationRequest convocationRequest);

    ConvocationResponse getConvocationById(UUID id);
}
