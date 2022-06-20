package com.kkp.pelatihanwebservice.internal.services.maritalStatus;

import com.kkp.pelatihanwebservice.internal.models.MaritalStatus;
import com.kkp.pelatihanwebservice.internal.repositories.MaritalStatusRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@TransactionScoped
public class MaritalStatusServiceImpl implements MaritalStatusService {
    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    public MaritalStatusServiceImpl(MaritalStatusRepository maritalStatusRepository) {
        super();
        this.maritalStatusRepository = maritalStatusRepository;
    }

    public Iterable<MaritalStatus> getAllMaritalStatus(String name, Pageable pageable) {
        return maritalStatusRepository.findByNamaStatusPernikahanContains(name, pageable);
    }

    public MaritalStatus getMaritalStatusDetail(Long id) {
        return maritalStatusRepository.findMaritalStatusById(id);
    }

    @Override
    public MaritalStatus createMaritalStatus(MaritalStatus maritalStatus) {
        return maritalStatusRepository.save(maritalStatus);
    }

    @Override
    public MaritalStatus updateMaritalStatus(Long id, MaritalStatus maritalStatus) {
        MaritalStatus existingMaritalStatus = maritalStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Status Pernikahan", "ID", id));

        existingMaritalStatus.setKodeStatusPernikahan(maritalStatus.getKodeStatusPernikahan());
        existingMaritalStatus.setNamaStatusPernikahan(maritalStatus.getNamaStatusPernikahan());
        existingMaritalStatus.setUpdatedAt(LocalDateTime.now());

        maritalStatusRepository.save(existingMaritalStatus);

        return existingMaritalStatus;
    }

    @Override
    public MaritalStatus deleteMaritalStatus(Long id) {
        MaritalStatus existingMaritalStatus = maritalStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Status Pernikahan", "ID", id));

        maritalStatusRepository.delete(existingMaritalStatus);
        return existingMaritalStatus;
    }
}
