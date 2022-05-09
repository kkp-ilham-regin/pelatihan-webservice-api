package com.kkp.pelatihanwebservice.internal.services.training;

import com.kkp.pelatihanwebservice.internal.models.Training;
import com.kkp.pelatihanwebservice.internal.repositories.TrainingRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;

@Service
@TransactionScoped
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        super();
        this.trainingRepository = trainingRepository;
    }

    public Iterable<Training> findAllTraining(String name, Pageable pageable) {
        return trainingRepository.findByNamaContains(name, pageable);
    }

    public Training findTrainingDetail(Long id) {
        return trainingRepository.findTrainingById(id);
    }

    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, Training training) {
        Training existingTraining = trainingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Training", "ID", id));

        existingTraining.setNama(training.getNama());
        existingTraining.setKode(training.getKode());
        existingTraining.setUpdatedAt(LocalDateTime.now());
        trainingRepository.save(existingTraining);

        return existingTraining;
    }

    @Override
    public Training deleteTraining(Long id) {
        Training existingTraining = trainingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Training", "ID", id));
        trainingRepository.delete(existingTraining);

        return existingTraining;
    }
}
