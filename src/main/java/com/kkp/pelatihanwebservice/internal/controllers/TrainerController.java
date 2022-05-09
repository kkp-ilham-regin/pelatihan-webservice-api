package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.trainer.request.TrainerRequest;
import com.kkp.pelatihanwebservice.internal.dto.trainer.response.TrainerResponse;
import com.kkp.pelatihanwebservice.internal.models.Trainer;
import com.kkp.pelatihanwebservice.internal.repositories.TrainerRepository;
import com.kkp.pelatihanwebservice.internal.services.trainer.TrainerService;
import com.kkp.pelatihanwebservice.internal.services.trainer.TrainerServiceImpl;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1/trainers")
public class TrainerController {

    private TrainerService trainerService;

    @Autowired
    private TrainerServiceImpl trainerServiceImpl;

    @Autowired
    TrainerRepository trainerRepository;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("")
    public Iterable<Trainer> trainerList(@RequestParam(required = false, value = "search", defaultValue = "") String searchData,
                                         @RequestParam(required = false, value = "size", defaultValue = "5") int size,
                                         @RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                         @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort)
            throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }
            return trainerServiceImpl.findAllTrainer(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object trainerDetail(@PathVariable("id") Long id) {
        if (trainerServiceImpl.findTrainerDetail(id) == null) {
            return new ResourceNotFoundException("Trainer", "ID", id);
        }
        return trainerServiceImpl.findTrainerDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<TrainerResponse<Trainer>> trainerCreate(@Valid @RequestBody TrainerRequest trainerRequest,
                                                                  Errors errors) {
        TrainerResponse<Trainer> responseData = new TrainerResponse<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setCode(400);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Trainer trainer = new Trainer(trainerRequest.getNama(), trainerRequest.getNik(), trainerRequest.getAlamat(),
                trainerRequest.getNomorTelepon(), trainerRequest.getEmail(), trainerRequest.getCreatedAt(),
                trainerRequest.getUpdatedAt());
        responseData.setStatus(true);
        responseData.setData(trainerServiceImpl.createTrainer(trainer));
        responseData.setCode(200);

        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerResponse<Trainer>> trainerUpdate(@Valid @RequestBody TrainerRequest trainerRequest,
                                                                  Errors errors, @PathVariable("id") Long id) {
        TrainerResponse<Trainer> responseData = new TrainerResponse<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setCode(400);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Trainer trainer = new Trainer(trainerRequest.getNama(), trainerRequest.getNik(), trainerRequest.getAlamat(),
                trainerRequest.getNomorTelepon(), trainerRequest.getEmail(), trainerRequest.getCreatedAt(),
                trainerRequest.getUpdatedAt());
        responseData.setStatus(true);
        responseData.setCode(200);
        responseData.setData(trainerServiceImpl.updateTrainer(id, trainer));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public Trainer trainerDelete(@PathVariable("id") Long id) {
        return trainerServiceImpl.deleteTrainer(id);
    }
}
