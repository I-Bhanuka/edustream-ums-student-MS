package com.example.edustream_studentMS.repository;

import com.example.edustream_studentMS.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<UUID, Student> {
}
