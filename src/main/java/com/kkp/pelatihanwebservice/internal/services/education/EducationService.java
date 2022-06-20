package com.kkp.pelatihanwebservice.internal.services.education;

import com.kkp.pelatihanwebservice.internal.models.Education;
import org.springframework.stereotype.Service;

public interface EducationService {
    Education createEducation(Education education);

    Education updateEducation(Long id, Education education);

    Education deleteEducation(Long id);
}
