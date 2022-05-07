package com.kkp.pelatihanwebservice.internal.services.gender;

import com.kkp.pelatihanwebservice.internal.models.Gender;
import com.kkp.pelatihanwebservice.internal.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenderService {
    @Autowired
    GenderRepository genderRepository;

    public List<Gender> getAllGender() {
        List<Gender> genders = new ArrayList<Gender>();
        genderRepository.findAll().forEach(gender -> genders.add(gender));
        return genders;
    }
}
