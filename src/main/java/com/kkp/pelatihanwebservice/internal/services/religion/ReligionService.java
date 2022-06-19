package com.kkp.pelatihanwebservice.internal.services.religion;

import com.kkp.pelatihanwebservice.internal.models.Religion;
import org.springframework.stereotype.Service;

@Service
public interface ReligionService {
    Religion createReligion(Religion religion);

    Religion updateReligion(Long id, Religion religion);

    Religion deleteReligion(Long id);
}
