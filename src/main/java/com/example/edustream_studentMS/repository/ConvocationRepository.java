package com.example.edustream_studentMS.repository;

import com.example.edustream_studentMS.entity.Convocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConvocationRepository extends JpaRepository<Convocation, UUID> {

    // Return only intakes that are not deleted
//    List<Convocation> findAllByDeletedFalse();
}
