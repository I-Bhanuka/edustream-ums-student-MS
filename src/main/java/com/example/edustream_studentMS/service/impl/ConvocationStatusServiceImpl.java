package com.example.edustream_studentMS.service.impl;

import com.example.edustream_studentMS.dto.responseDTO.ConvocationStatusResponse;
import com.example.edustream_studentMS.entity.ConvocationStatus;
import com.example.edustream_studentMS.repository.ConvocationStatusRepository;
import com.example.edustream_studentMS.service.ConvocationStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConvocationStatusServiceImpl implements ConvocationStatusService {

    private final ConvocationStatusRepository convocationStatusRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ConvocationStatusResponse> getAllConvocationStatus() {
        log.info("================================ Get All Convocation Status ==============================");

        List<ConvocationStatus> convocationStatusList = convocationStatusRepository.findAll();

        if (convocationStatusList.isEmpty()) {
            log.warn("No Convocation Status found in the database");
            return List.of(); // Return an empty list if no records found
        }

        log.info("Convocation Status List: {}", convocationStatusList);

        return convocationStatusList.stream()
                .map(this::mapToConvocationStatusResponse)
                .toList();
    }


    // Helper Methods
    private ConvocationStatusResponse mapToConvocationStatusResponse(ConvocationStatus convocationStatus) {
        return ConvocationStatusResponse.builder()
                .id(convocationStatus.getId())
                .status(convocationStatus.getStatus())
                .description(convocationStatus.getDescription())
                .build();
    }
}
