package com.example.edustream_studentMS.repository;

import com.example.edustream_studentMS.entity.ConvocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConvocationStatusRepository extends JpaRepository<ConvocationStatus, UUID> {

    Optional<ConvocationStatus> findByStatus(String code);
}
