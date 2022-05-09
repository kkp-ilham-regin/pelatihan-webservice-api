package com.kkp.pelatihanwebservice.internal.services.trainer;

import com.kkp.pelatihanwebservice.internal.models.Trainer;
import com.kkp.pelatihanwebservice.internal.repositories.TrainerRepository;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;

@Service
@TransactionScoped
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    TrainerRepository trainerRepository;


    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        super();
        this.trainerRepository = trainerRepository;
    }

    public Iterable<Trainer> findAllTrainer(String name, Pageable pageable) {
        return trainerRepository.findByNamaContains(name, pageable);
    }

    public Trainer findTrainerDetail(Long id) {
        return trainerRepository.findTrainerById(id);
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer updateTrainer(Long id, Trainer trainer) {
        Trainer existingTrainer = trainerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Trainer", "ID", id));

        existingTrainer.setNama(trainer.getNama());
        existingTrainer.setNik(trainer.getNik());
        existingTrainer.setAlamat(trainer.getAlamat());
        existingTrainer.setNomorTelepon(trainer.getNomorTelepon());
        existingTrainer.setEmail(trainer.getEmail());
        existingTrainer.setUpdatedAt(LocalDateTime.now());

        trainerRepository.save(existingTrainer);
        return existingTrainer;
    }

    @Override
    public Trainer deleteTrainer(Long id) {
        Trainer existingTrainer = trainerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Trainer", "ID", id));

        trainerRepository.delete(existingTrainer);
        return existingTrainer;
    }
}
