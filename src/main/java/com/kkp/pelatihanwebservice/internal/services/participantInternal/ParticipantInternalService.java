package com.kkp.pelatihanwebservice.internal.services.participantInternal;

import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;

public interface ParticipantInternalService {
    ParticipantInternal createParticipantInternal(ParticipantInternal participantInternal);

    ParticipantInternal updateParticipantInternal(Long id, ParticipantInternal participantInternal);

    ParticipantInternal updateHasCertificateParticipant(Long id, boolean hasCertificate);

    ParticipantInternal deleteParticipantInternal(Long id);
}
