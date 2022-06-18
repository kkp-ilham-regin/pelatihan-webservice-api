package com.kkp.pelatihanwebservice.internal.services.gender;

import com.kkp.pelatihanwebservice.internal.models.Gender;
import com.kkp.pelatihanwebservice.internal.repositories.GenderRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;

@Service
@TransactionScoped
public class GenderServiceImpl implements GenderService {

    @Autowired
    GenderRepository genderRepository;

    public GenderServiceImpl(GenderRepository genderRepository) {
        super();
        this.genderRepository = genderRepository;
    }

    public Iterable<Gender> findAllGender(String name, Pageable pageable) {
        return genderRepository.findByNamaJenisKelaminContains(name, pageable);
    }

    public Gender getGenderDetail(Long id) {
        return genderRepository.findGenderById(id);
    }

    @Override
    public Gender createGender(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public Gender updateGender(Long id, Gender gender) {
        Gender existingGender = genderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Jenis Kelamin", "ID", id));

        existingGender.setKodeJenisKelamin(gender.getKodeJenisKelamin());
        existingGender.setNamaJenisKelamin(gender.getNamaJenisKelamin());
        existingGender.setUpdatedAt(LocalDateTime.now());

        genderRepository.save(existingGender);
        return existingGender;
    }

    @Override
    public Gender deleteGender(Long id) {
        Gender existingGender = genderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Jenis Kelamin", "ID", id));

        genderRepository.delete(existingGender);
        return existingGender;
    }
}
