package com.example.edustream_studentMS.repository;

import com.example.edustream_studentMS.entity.ConvocationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ConvocationSessionRepository extends JpaRepository<ConvocationSession, UUID> {

    @Query("SELECT cs FROM ConvocationSession cs " +
            "WHERE cs.convocation.id = :convocationId " +
            "ORDER BY cs.createdAt DESC")
    List<ConvocationSession> findByConvocationId(UUID convocationId);
}
