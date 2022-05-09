package com.kkp.pelatihanwebservice.internal.services.training;

import com.kkp.pelatihanwebservice.internal.models.Training;

public interface TrainingService {
    Training createTraining(Training training);

    Training updateTraining(Long id, Training training);

    Training deleteTraining(Long id);
}
