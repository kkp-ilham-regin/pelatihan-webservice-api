package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantInternalRepository extends JpaRepository<ParticipantInternal, Long> {
    Page<ParticipantInternal> findByNamaPesertaContains(String name, Pageable pageable);

    ParticipantInternal findParticipantInternalById(Long id);

    Optional<ParticipantInternal> findById(Long id);

    Page<ParticipantInternal> findAllByIdPenawaran(Long offerId, Pageable pageable);
}
