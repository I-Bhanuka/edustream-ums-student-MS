package com.example.edustream_studentMS.service;

import com.example.edustream_studentMS.dto.responseDTO.ConvocationStatusResponse;

import java.util.List;

public interface ConvocationStatusService {

    List<ConvocationStatusResponse> getAllConvocationStatus();
}
