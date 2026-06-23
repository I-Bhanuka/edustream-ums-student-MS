package com.example.edustream_studentMS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "convocation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Convocation {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    // DDL: name nvarchar(255) NOT NULL
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    // DDL: year smallint NOT NULL → use short because of 16 bits
    @Column(name = "year", nullable = false)
    private short year;

    // DDL: payment decimal(10,2) NULL → BigDecimal to handle for money values accurately
    @Column(name = "payment", precision = 10, scale = 2)
    private BigDecimal payment;

    // DDL: suppliment_open_date date NULL (note: DDL has a typo "suppliment")
    @Column(name = "suppliment_open_date")
    private LocalDate supplementOpenDate;

    // DDL: suppliment_end_date date NULL
    @Column(name = "suppliment_end_date")
    private LocalDate supplementEndDate;

    // DDL: status_id uniqueidentifier NOT NULL → FK column must match DDL name
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private ConvocationStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // DDL: created_by uniqueidentifier NULL
    // Using String instead of UUID because the security context stores usernames
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // DDL: updated_by uniqueidentifier NULL
    // Using String instead of UUID because the security context stores usernames
    @Column(name = "updated_by")
    private String updatedBy;

    // DDL: updated_by_ip nvarchar(45) NULL
    @Column(name = "updated_by_ip", length = 45)
    private String updatedByIp;

    // DDL: deleted_at datetime2 NULL
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // DDL: deleted_by uniqueidentifier NULL
    // Using String instead of UUID because the security context stores usernames
    @Column(name = "deleted_by")
    private String deletedBy;

    // Do we need it?
    // private boolean deleted;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}