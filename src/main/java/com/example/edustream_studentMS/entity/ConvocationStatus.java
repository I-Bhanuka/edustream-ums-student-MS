package com.example.edustream_studentMS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "convocation_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ConvocationStatus {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "status", length = 45, nullable = false)
    private String status;

    @Column(name = "description", length = 45, nullable = false)
    private String description;
}
