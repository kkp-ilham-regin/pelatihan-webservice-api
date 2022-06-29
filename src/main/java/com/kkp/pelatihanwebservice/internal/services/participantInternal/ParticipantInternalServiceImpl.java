package com.kkp.pelatihanwebservice.internal.services.participantInternal;

import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;
import com.kkp.pelatihanwebservice.internal.repositories.ParticipantInternalRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParticipantInternalServiceImpl implements ParticipantInternalService {
    @Autowired
    ParticipantInternalRepository participantInternalRepository;

    public ParticipantInternalServiceImpl(ParticipantInternalRepository participantInternalRepository) {
        super();
        this.participantInternalRepository = participantInternalRepository;
    }

    public Iterable<ParticipantInternal> findAllParticipantInternal(String name, Pageable pageable) {
        return participantInternalRepository.findByNamaPesertaContains(name, pageable);
    }

    public ParticipantInternal findParticipantInternalById(Long id) {
        return participantInternalRepository.findParticipantInternalById(id);
    }

    @Override
    public ParticipantInternal createParticipantInternal(ParticipantInternal participantInternal) {
        return participantInternalRepository.save(participantInternal);
    }

    @Override
    public ParticipantInternal updateParticipantInternal(Long id, ParticipantInternal participantInternal) {
        ParticipantInternal existingParticipant = participantInternalRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Peserta", "ID", id));

        existingParticipant.setNamaPeserta(participantInternal.getNamaPeserta());
        existingParticipant.setNip(participantInternal.getNip());
        existingParticipant.setNomorKta(participantInternal.getNomorKta());
        existingParticipant.setExpiredKta(participantInternal.getExpiredKta());
        existingParticipant.setJabatan(participantInternal.getJabatan());
        existingParticipant.setLokasiKerja(participantInternal.getLokasiKerja());
        existingParticipant.setNik(participantInternal.getNik());
        existingParticipant.setTanggalTmtMasuk(participantInternal.getTanggalTmtMasuk());
        existingParticipant.setAlamat(participantInternal.getAlamat());
        existingParticipant.setTempatLahir(participantInternal.getTempatLahir());
        existingParticipant.setTanggalLahir(participantInternal.getTanggalLahir());
        existingParticipant.setGolonganDarah(participantInternal.getGolonganDarah());
        existingParticipant.setNomorTelepon(participantInternal.getNomorTelepon());
        existingParticipant.setNamaSekolah(participantInternal.getNamaSekolah());
        existingParticipant.setEmail(participantInternal.getEmail());
        existingParticipant.setNpwp(participantInternal.getNpwp());
        existingParticipant.setUrlImage(participantInternal.getUrlImage());
        existingParticipant.setIdWilayah(participantInternal.getIdWilayah());
        existingParticipant.setIdJenisKelamin(participantInternal.getIdJenisKelamin());
        existingParticipant.setIdAgama(participantInternal.getIdAgama());
        existingParticipant.setIdStatusPernikahan(participantInternal.getIdStatusPernikahan());
        existingParticipant.setIdPendidikan(participantInternal.getIdPendidikan());
        existingParticipant.setIdStatusPegawai(participantInternal.getIdStatusPegawai());
        existingParticipant.setUpdatedAt(LocalDateTime.now());

        existingParticipant.setWilayah(participantInternal.getWilayah());
        existingParticipant.setJenisKelamin(participantInternal.getJenisKelamin());
        existingParticipant.setAgama(participantInternal.getAgama());
        existingParticipant.setStatusPernikahan(participantInternal.getStatusPernikahan());
        existingParticipant.setPendidikan(participantInternal.getPendidikan());
        existingParticipant.setStatusPegawai(participantInternal.getStatusPegawai());

        participantInternalRepository.save(existingParticipant);
        return existingParticipant;
    }

    @Override
    public ParticipantInternal deleteParticipantInternal(Long id) {
        ParticipantInternal existingParticipant = participantInternalRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Peserta", "ID", id));

        participantInternalRepository.delete(existingParticipant);
        return existingParticipant;
    }
}
