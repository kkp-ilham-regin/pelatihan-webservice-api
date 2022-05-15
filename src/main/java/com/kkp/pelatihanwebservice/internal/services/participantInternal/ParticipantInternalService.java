package com.kkp.pelatihanwebservice.internal.services.participantInternal;

import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;
import com.kkp.pelatihanwebservice.internal.repositories.ParticipantInternalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ParticipantInternalService {
    @Autowired
    ParticipantInternalRepository participantInternalRepository;

    public ParticipantInternalService(ParticipantInternalRepository participantInternalRepository) {
        super();
        this.participantInternalRepository = participantInternalRepository;
    }

    public Iterable<ParticipantInternal> findAllParticipantInternal(String name, Pageable pageable) {
        return participantInternalRepository.findByNamaPesertaContains(name, pageable);
    }

    public ParticipantInternal findParticipantInternalById(Long id) {
        return participantInternalRepository.findParticipantInternalById(id);
    }
}
