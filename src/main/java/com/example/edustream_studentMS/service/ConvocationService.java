package com.example.edustream_studentMS.service;

import com.example.edustream_studentMS.dto.requestDTO.ConvocationRequest;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationAddResponse;

public interface ConvocationService {

    ConvocationAddResponse createConvocation(ConvocationRequest convocationRequest);
}
