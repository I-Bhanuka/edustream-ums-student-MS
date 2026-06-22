package com.example.edustream_studentMS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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

    @Column(name = "convocation_name", length = 100, nullable = false)
    private String convocationName;

    @Column(name = "convocation_year", nullable = false)
    private int convocationYear;

    @Column(name = "convocation_payment", nullable = false)
    private double convocationPayment;

    @Column(name = "supplicant_open_date", nullable = false)
    private LocalDate supplicantOpenDate;

    @Column(name = "supplicant_end_date", nullable = false)
    private LocalDate supplicantEndDate;

    @JoinColumn(name = "convocation_status_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY) // Many records from this table will refer to one record on the other
    private ConvocationStatus convocationStatusId;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private UUID updatedBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "delete_remarks", length = 1000)
    private String deleteRemarks;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    private UUID deletedBy;

}
