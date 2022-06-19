package com.kkp.pelatihanwebservice.internal.services.religion;

import com.kkp.pelatihanwebservice.internal.models.Religion;
import com.kkp.pelatihanwebservice.internal.repositories.ReligionRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;

@Service
@TransactionScoped
public class ReligionServiceImpl implements ReligionService {

    @Autowired
    ReligionRepository religionRepository;

    public ReligionServiceImpl(ReligionRepository religionRepository) {
        super();
        this.religionRepository = religionRepository;
    }

    public Iterable<Religion> findAllReligion(String name, Pageable pageable) {
        return religionRepository.findByNamaAgamaContains(name, pageable);
    }

    public Religion getReligionDetail(Long id) {
        return religionRepository.findReligionById(id);
    }

    @Override
    public Religion createReligion(Religion religion) {
        return religionRepository.save(religion);
    }

    @Override
    public Religion updateReligion(Long id, Religion religion) {
        Religion existingReligion = religionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Agama", "ID", id));

        existingReligion.setKodeAgama(religion.getKodeAgama());
        existingReligion.setNamaAgama(religion.getNamaAgama());
        existingReligion.setUpdatedAt(LocalDateTime.now());

        religionRepository.save(existingReligion);
        return existingReligion;
    }

    @Override
    public Religion deleteReligion(Long id) {
        Religion existingReligion = religionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Agama", "ID", id));

        religionRepository.delete(existingReligion);
        return existingReligion;
    }
}
