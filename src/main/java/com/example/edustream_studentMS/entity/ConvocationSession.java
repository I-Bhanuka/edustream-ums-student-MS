package com.example.edustream_studentMS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "convocation_session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ConvocationSession {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    // DDL: convocation_id NOT NULL → FK to convocation table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "convocation_id", nullable = false)
    private Convocation convocation;

    // DDL: name nvarchar(255) NOT NULL
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    // DDL: session_date date NULL
    @Column(name = "session_date")
    private LocalDate sessionDate;

    // DDL: from_time datetime NULL → LocalDateTime (no timezone needed)
    @Column(name = "from_time")
    private LocalDateTime fromTime;

    // DDL: to_time datetime NULL → LocalDateTime (no timezone needed)
    @Column(name = "to_time")
    private LocalDateTime toTime;

    // DDL: capacity smallint NULL → short for 16-bit integer
    @Column(name = "capacity")
    private Short capacity;

    // DDL: no_of_passes smallint NULL → short for 16-bit integer
    @Column(name = "no_of_passes")
    private Short noOfPasses;

    // DDL: no_of_staff smallint NULL → short for 16-bit integer
    @Column(name = "no_of_staff")
    private Short noOfStaff;

    // DDL: chief_guest nvarchar(255) NULL
    @Column(name = "chief_guest", length = 255)
    private String chiefGuest;

    // DDL: status_id NOT NULL → FK to mt_convocation_session_status table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private ConvocationSessionStatus status;

    // DDL: reject_reason nvarchar(2000) NULL
    @Column(name = "reject_reason", length = 2000)
    private String rejectReason;

    // DDL: finalized_reason nvarchar(2000) NULL
    @Column(name = "finalized_reason", length = 2000)
    private String finalizedReason;

    // DDL: approved_or_rejected_at datetime2 NULL
    @Column(name = "approved_or_rejected_at")
    private LocalDateTime approvedOrRejectedAt;

    // DDL: approved_or_rejected_by uniqueidentifier NULL
    // Using String instead of UUID to align with security context username pattern
    @Column(name = "approved_or_rejected_by")
    private String approvedOrRejectedBy;

    // DDL: created_at datetime2 NOT NULL
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // DDL: created_by uniqueidentifier NULL
    // Using String instead of UUID because the security context stores usernames
    @Column(name = "created_by")
    private String createdBy;

    // DDL: updated_at datetime2 NOT NULL
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