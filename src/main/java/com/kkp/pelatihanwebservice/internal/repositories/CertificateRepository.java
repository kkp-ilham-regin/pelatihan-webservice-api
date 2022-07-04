package com.kkp.pelatihanwebservice.internal.repositories;

import com.kkp.pelatihanwebservice.internal.models.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Page<Certificate> findAllByKodeSertifikatContains(String name, Pageable pageable);

    Certificate findCertificateById(Long id);

    Optional<Certificate> findById(Long id);

    Certificate findCertificateByIdPesertaAndIdPenawaran(Long pesertaId, Long penawaranId);
}
