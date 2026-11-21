package com.yo.elderlycarebooking.repository;

import com.yo.elderlycarebooking.domain.entity.Caregiver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {
    //CHỐNG LỖI QUERY N + 1
    @EntityGraph(attributePaths = {"user"})
    @Query("""
            SELECT c FROM Caregiver c
            JOIN c.user u
            WHERE (:isAvailable IS NULL OR c.isAvailable = :isAvailable)
              AND (:minExperience IS NULL OR c.experienceYears >= :minExperience)
              AND (:minRating IS NULL OR c.avgRating >= :minRating)
              AND (:keyword IS NULL OR :keyword = ''
                   OR LOWER(c.qualification) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(COALESCE(c.bio, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    Page<Caregiver> searchCaregivers(
            @Param("isAvailable") Boolean isAvailable,
            @Param("minExperience") Integer minExperience,
            @Param("minRating") BigDecimal minRating,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"user"})
    Optional<Caregiver> findByUserId(Long userId);

    @EntityGraph(attributePaths = {"user"})
    Optional<Caregiver> findByUserUsername(String username);

    boolean existsByUserId(Long userId);
}
