package com.kkp.pelatihanwebservice.internal.services.gender;

import com.kkp.pelatihanwebservice.internal.models.Gender;

public interface GenderService {
    Gender createGender(Gender gender);

    Gender updateGender(Long id, Gender gender);

    Gender deleteGender(Long id);
}
