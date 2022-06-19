package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.Religion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long> {
    Page<Religion> findByNamaAgamaContains(String name, Pageable pageable);

    Religion findReligionById(Long id);

    Optional<Religion> findById(Long id);
}
