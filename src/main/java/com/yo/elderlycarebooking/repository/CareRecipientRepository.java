package com.yo.elderlycarebooking.repository;

import com.yo.elderlycarebooking.domain.entity.CareRecipient;
import com.yo.elderlycarebooking.domain.enumm.GenderEnum;
import com.yo.elderlycarebooking.domain.enumm.MobilityEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareRecipientRepository extends JpaRepository<CareRecipient, Long> {
    @Override
    @EntityGraph(attributePaths = {"customer"})
    Optional<CareRecipient> findById(Long id);

    @EntityGraph(attributePaths = {"customer"})
    List<CareRecipient> findAllByCustomerUsernameOrderByCreatedAtDesc(String username);

    @EntityGraph(attributePaths = {"customer"})
    @Query("""
            SELECT r FROM CareRecipient r
            JOIN r.customer c
            WHERE (:customerId IS NULL OR c.id = :customerId)
              AND (:mobilityLevel IS NULL OR r.mobilityLevel = :mobilityLevel)
              AND (:gender IS NULL OR r.gender = :gender)
              AND (:keyword IS NULL OR :keyword = ''
                   OR LOWER(r.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(COALESCE(r.medicalNotes, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(c.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    Page<CareRecipient> searchCareRecipients(
            @Param("customerId") Long customerId,
            @Param("mobilityLevel") MobilityEnum mobilityLevel,
            @Param("gender") GenderEnum gender,
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
