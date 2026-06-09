package com.example.edustream_studentMS.repository;

import com.example.edustream_studentMS.entity.Convocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConvocationRepository extends JpaRepository<Convocation, UUID> {
}
