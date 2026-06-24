package com.example.edustream_studentMS.repository;

import com.example.edustream_studentMS.entity.Convocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ConvocationRepository extends JpaRepository<Convocation, UUID> {

    // Return only intakes that are not deleted
//    List<Convocation> findAllByDeletedFalse();

    // No need to apply on, just can use the field of the JOIN entity

    // If the name is not null then skip that filer or apply the filter
    // Similar with others as well

    @Query("SELECT c FROM Convocation c " +
            "JOIN c.status cs " +
            "WHERE (:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', CAST(:name AS string), '%'))) " +
            "AND (:year IS NULL OR c.year = :year) " +
            "AND (:status IS NULL OR cs.status = CAST(:status AS string))")
    List<Convocation> searchConvocations(
            @Param("name") String name,
            @Param("year") Short year,
            @Param("status") String status
    );
}
