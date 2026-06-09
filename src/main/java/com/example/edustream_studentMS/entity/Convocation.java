package com.example.edustream_studentMS.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
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
}
