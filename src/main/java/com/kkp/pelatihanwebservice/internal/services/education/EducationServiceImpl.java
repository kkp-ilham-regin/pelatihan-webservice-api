package com.kkp.pelatihanwebservice.internal.services.education;

import com.kkp.pelatihanwebservice.internal.models.Education;
import com.kkp.pelatihanwebservice.internal.repositories.EducationRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    EducationRepository educationRepository;

    public EducationServiceImpl(EducationRepository educationRepository) {
        super();
        this.educationRepository = educationRepository;
    }

    public Iterable<Education> getAllEducation(String name, Pageable pageable) {
        return educationRepository.findByNamaPendidikanContains(name, pageable);
    }

    public Education getEducationDetail(Long id) {
        return educationRepository.findEducationById(id);
    }

    @Override
    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    @Override
    public Education updateEducation(Long id, Education education) {
        Education existingEducation = educationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pendidikan", "ID", id));

        existingEducation.setKodePendidikan(education.getKodePendidikan());
        existingEducation.setNamaPendidikan(education.getNamaPendidikan());
        existingEducation.setUpdatedAt(LocalDateTime.now());
        educationRepository.save(existingEducation);
        return existingEducation;
    }

    @Override
    public Education deleteEducation(Long id) {
        Education existingEducation = educationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pendidikan", "ID", id));
        educationRepository.delete(existingEducation);
        return existingEducation;
    }
}
