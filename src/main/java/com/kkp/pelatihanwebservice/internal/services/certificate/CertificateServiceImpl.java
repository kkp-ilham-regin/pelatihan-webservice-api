package com.kkp.pelatihanwebservice.internal.services.certificate;

import com.kkp.pelatihanwebservice.internal.models.Certificate;
import com.kkp.pelatihanwebservice.internal.repositories.CertificateRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;

@Service
@TransactionScoped
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        super();
        this.certificateRepository = certificateRepository;
    }

    public Iterable<Certificate> findAllCertificate(String name, Pageable pageable) {
        return certificateRepository.findAllByKodeSertifikatContains(name, pageable);
    }

    public Certificate findParticipantCertificateBelongstoOfferDetail(Long participantId, Long offerId) {
        return certificateRepository.findCertificateByIdPesertaAndIdPenawaran(participantId, offerId);
    }

    public Certificate findCertificateById(Long id) {
        return certificateRepository.findCertificateById(id);
    }

    @Override
    public Certificate createCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate updateCertificate(Long id, Certificate certificate) {
        Certificate existingCertificate = certificateRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sertifikat", "ID", id));

        existingCertificate.setKodeSertifikat(certificate.getKodeSertifikat());
        existingCertificate.setTanggalMulaiPelatihan(certificate.getTanggalMulaiPelatihan());
        existingCertificate.setTanggalSelesaiPelatihan(certificate.getTanggalSelesaiPelatihan());
        existingCertificate.setTanggalExpired(certificate.getTanggalExpired());
        existingCertificate.setNamaLembaga(certificate.getNamaLembaga());
        existingCertificate.setLokasi(certificate.getLokasi());
        existingCertificate.setFileSertifikat(certificate.getFileSertifikat());
        existingCertificate.setIdPeserta(certificate.getIdPeserta());
        existingCertificate.setIdTrainer(certificate.getIdTrainer());
        existingCertificate.setIdPelatihan(certificate.getIdPelatihan());
        existingCertificate.setUpdatedAt(LocalDateTime.now());

        existingCertificate.setPeserta(certificate.getPeserta());
        existingCertificate.setTrainer(certificate.getTrainer());
        existingCertificate.setPelatihan(certificate.getPelatihan());
        certificateRepository.save(existingCertificate);
        return existingCertificate;
    }

    @Override
    public Certificate deleteCertificate(Long id) {
        Certificate existingCertificate = certificateRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sertifikat", "ID", id));

        certificateRepository.deleteById(id);
        return existingCertificate;
    }
}
