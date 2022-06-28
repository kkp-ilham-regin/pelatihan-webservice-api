package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findStatusById(Long id);

    Optional<Status> findById(Long id);
}
