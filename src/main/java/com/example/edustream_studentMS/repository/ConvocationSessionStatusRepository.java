package com.example.edustream_studentMS.repository;

import com.example.edustream_studentMS.entity.ConvocationSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConvocationSessionStatusRepository extends JpaRepository<ConvocationSessionStatus, UUID> {

    Optional<ConvocationSessionStatus> findByStatus(String code);
}

