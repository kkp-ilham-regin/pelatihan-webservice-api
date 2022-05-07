package com.kkp.pelatihanwebservice.internal.services.religion;

import com.kkp.pelatihanwebservice.internal.models.Religion;
import com.kkp.pelatihanwebservice.internal.repositories.ReligionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReligionService {
    @Autowired
    ReligionRepository religionRepository;

    public List<Religion> getAllReligion() {
        List<Religion> religions = new ArrayList<Religion>();
        religionRepository.findAll().forEach(religion -> religions.add(religion));
        return religions;
    }
}
