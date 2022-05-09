package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.training.request.TrainingRequest;
import com.kkp.pelatihanwebservice.internal.dto.training.response.TrainingResponse;
import com.kkp.pelatihanwebservice.internal.models.Training;
import com.kkp.pelatihanwebservice.internal.repositories.TrainingRepository;
import com.kkp.pelatihanwebservice.internal.services.training.TrainingService;
import com.kkp.pelatihanwebservice.internal.services.training.TrainingServiceImpl;
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
@RequestMapping("/pelatihan-webservice/internal-api/v1/trainings")
public class TrainingController {
    private TrainingService trainingService;

    @Autowired
    TrainingServiceImpl trainingServiceImpl;

    @Autowired
    TrainingRepository trainingRepository;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("")
    public Iterable<Training> trainingList(@RequestParam(required = false, value = "search", defaultValue = "") String searchData,
                                           @RequestParam(required = false, value = "size", defaultValue = "5") int size,
                                           @RequestParam(required = false, value = "page", defaultValue = "") int page,
                                           @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort)
            throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }
            return trainingServiceImpl.findAllTraining(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object trainingDetail(@PathVariable("id") Long id) {
        if (trainingServiceImpl.findTrainingDetail(id) == null) {
            return new ResourceNotFoundException("Training", "ID", id);
        }
        return trainingServiceImpl.findTrainingDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<TrainingResponse<Training>> trainingCreate(@Valid @RequestBody TrainingRequest trainingRequest,
                                                                     Errors errors) {
        TrainingResponse<Training> responseData = new TrainingResponse<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setCode(400);
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Training training = new Training(trainingRequest.getNama(), trainingRequest.getKode(), trainingRequest.getCreatedAt(),
                trainingRequest.getUpdatedAt());

        responseData.setCode(200);
        responseData.setStatus(true);
        responseData.setData(trainingServiceImpl.createTraining(training));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingResponse<Training>> trainingUpdate(@Valid @RequestBody TrainingRequest trainingRequest,
                                                                     Errors errors, @PathVariable("id") Long id) {
        TrainingResponse<Training> responseData = new TrainingResponse<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setCode(400);
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Training training = new Training(trainingRequest.getNama(), trainingRequest.getKode(), trainingRequest.getCreatedAt(),
                trainingRequest.getUpdatedAt());
        responseData.setCode(200);
        responseData.setStatus(true);
        responseData.setData(trainingServiceImpl.updateTraining(id, training));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    Training trainingDelete(@PathVariable("id") Long id) {
        return trainingServiceImpl.deleteTraining(id);
    }

}
