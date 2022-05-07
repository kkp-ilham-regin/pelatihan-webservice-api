package com.kkp.pelatihanwebservice.internal.services.education;

import com.kkp.pelatihanwebservice.internal.models.Education;
import com.kkp.pelatihanwebservice.internal.repositories.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationService {
    @Autowired
    EducationRepository educationRepository;

    public List<Education> getAllEducation() {
        List<Education> educations = new ArrayList<Education>();
        educationRepository.findAll().forEach(education -> educations.add(education));
        return educations;
    }
}
