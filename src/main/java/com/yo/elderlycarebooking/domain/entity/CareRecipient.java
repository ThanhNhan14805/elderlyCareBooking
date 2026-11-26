package com.yo.elderlycarebooking.domain.entity;

import com.yo.elderlycarebooking.domain.enumm.GenderEnum;
import com.yo.elderlycarebooking.domain.enumm.MobilityEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "care_recipients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareRecipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipient_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private GenderEnum gender;

    @Column(name = "year_of_birth", nullable = false)
    private Integer yearOfBirth;

    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "mobility_level", nullable = false, length = 20)
    private MobilityEnum mobilityLevel = MobilityEnum.INDEPENDENT;

    @Column(name = "medical_notes", columnDefinition = "TEXT")
    private String medicalNotes;

    @Column(name = "special_notes", columnDefinition = "TEXT")
    private String specialNotes;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
}
