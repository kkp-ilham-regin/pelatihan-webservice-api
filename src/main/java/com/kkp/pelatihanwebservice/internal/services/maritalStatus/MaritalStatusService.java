package com.kkp.pelatihanwebservice.internal.services.maritalStatus;

import com.kkp.pelatihanwebservice.internal.models.MaritalStatus;
import com.kkp.pelatihanwebservice.internal.repositories.MaritalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaritalStatusService {
    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    public List<MaritalStatus> getAllMaritalStatus() {
        List<MaritalStatus> maritalStatuses = new ArrayList<MaritalStatus>();
        maritalStatusRepository.findAll().forEach(maritalStatus -> maritalStatuses.add(maritalStatus));
        return maritalStatuses;
    }
}
