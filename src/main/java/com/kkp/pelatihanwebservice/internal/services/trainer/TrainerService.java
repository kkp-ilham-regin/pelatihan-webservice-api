package com.kkp.pelatihanwebservice.internal.services.trainer;

import com.kkp.pelatihanwebservice.internal.models.Trainer;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer);

    Trainer updateTrainer(Long id, Trainer trainer);

    Trainer deleteTrainer(Long id);
}
