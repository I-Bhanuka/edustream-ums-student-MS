package com.example.edustream_studentMS.service;

import com.example.edustream_studentMS.dto.requestDTO.ConvocationSessionApproveRequest;
import com.example.edustream_studentMS.dto.requestDTO.ConvocationSessionRejectRequest;
import com.example.edustream_studentMS.dto.requestDTO.ConvocationSessionRequest;
import com.example.edustream_studentMS.dto.responseDTO.ConvocationSessionResponse;

import java.util.List;
import java.util.UUID;

public interface ConvocationSessionService {

    ConvocationSessionResponse createConvocationSession(ConvocationSessionRequest request);

    List<ConvocationSessionResponse> getConvocationSessionsByConvocationId(UUID convocationId);

    ConvocationSessionResponse approveConvocationSession(UUID convocationSessionId, ConvocationSessionApproveRequest request);

    ConvocationSessionResponse rejectConvocationSession(UUID convocationSessionId, ConvocationSessionRejectRequest request);
}
