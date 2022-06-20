package com.kkp.pelatihanwebservice.internal.services.maritalStatus;

import com.kkp.pelatihanwebservice.internal.models.MaritalStatus;

public interface MaritalStatusService {
    MaritalStatus createMaritalStatus(MaritalStatus maritalStatus);

    MaritalStatus updateMaritalStatus(Long id, MaritalStatus maritalStatus);

    MaritalStatus deleteMaritalStatus(Long id);
}
