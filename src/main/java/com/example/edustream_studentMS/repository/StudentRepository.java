package com.example.edustream_studentMS.repository;

import com.example.edustream_studentMS.dto.responseDTO.LimitedStudentResponseDTO;
import com.example.edustream_studentMS.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    /**
     * Query,
     * SELECT *
     * FROM student
     * ORDER BY created_at DESC
     * LIMIT 1;
     */
    Optional<Student> findTopByOrderByCreatedAtDesc();

    /**
     * Query,
     * SELECT *
     * FROM student
     * WHERE studentid = ?;
     */
    Optional<Student> findByStudentId(String studentId);

    @Query("""
        SELECT new com.example.edustream_studentMS.dto.responseDTO.LimitedStudentResponseDTO(
            s.studentId,
            s.firstName,
            s.lastName,
            s.email,
            s.dob,
            s.enrollmentDate,
            s.studentStatus,
            s.courseId
        )
        FROM Student s
    """
    )
    Page<LimitedStudentResponseDTO> findAllWithLimitedDetails(Pageable pageable);
}
