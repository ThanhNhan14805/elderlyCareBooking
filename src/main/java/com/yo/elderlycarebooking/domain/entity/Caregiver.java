package com.yo.elderlycarebooking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "caregivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Caregiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caregiver_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Default
    @Column(name = "experience_years", nullable = false)
    private Integer experienceYears = 0;

    @Column(name = "qualification", nullable = false, length = 100)
    private String qualification;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Default
    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    @Default
    @Column(name = "avg_rating", nullable = false, precision = 3, scale = 2)
    private BigDecimal avgRating = new BigDecimal("5.00");

    @Default
    @Column(name = "total_jobs_done", nullable = false)
    private Integer totalJobsDone = 0;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
}
