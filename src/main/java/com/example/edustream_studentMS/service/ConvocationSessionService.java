package com.example.edustream_studentMS.service;

import com.example.edustream_studentMS.dto.requestDTO.ConvocationSessionRequest;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationSessionResponse;

public interface ConvocationSessionService {

    ConvocationSessionResponse createConvocationSession(ConvocationSessionRequest request);
}
