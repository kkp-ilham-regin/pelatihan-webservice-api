package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.MaritalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Long> {
    Page<MaritalStatus> findByNamaStatusPernikahanContains(String name, Pageable pageable);

    MaritalStatus findMaritalStatusById(Long id);

    Optional<MaritalStatus> findById(Long id);
}
