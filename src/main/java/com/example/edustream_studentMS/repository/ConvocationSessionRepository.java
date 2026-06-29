package com.example.edustream_studentMS.repository;

import com.example.edustream_studentMS.entity.ConvocationSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConvocationSessionRepository extends JpaRepository<ConvocationSession, UUID> {
}
