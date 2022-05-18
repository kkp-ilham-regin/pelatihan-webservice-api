package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.certificate.request.CertificateRequest;
import com.kkp.pelatihanwebservice.internal.dto.certificate.response.CertificateResponse;
import com.kkp.pelatihanwebservice.internal.models.Certificate;
import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;
import com.kkp.pelatihanwebservice.internal.models.Trainer;
import com.kkp.pelatihanwebservice.internal.models.Training;
import com.kkp.pelatihanwebservice.internal.repositories.CertificateRepository;
import com.kkp.pelatihanwebservice.internal.repositories.ParticipantInternalRepository;
import com.kkp.pelatihanwebservice.internal.repositories.TrainerRepository;
import com.kkp.pelatihanwebservice.internal.repositories.TrainingRepository;
import com.kkp.pelatihanwebservice.internal.services.certificate.CertificateService;
import com.kkp.pelatihanwebservice.internal.services.certificate.CertificateServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1/certificates")
public class CertificateController {

    private CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @Autowired
    CertificateServiceImpl certificateServiceImpl;

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    ParticipantInternalRepository participantInternalRepository;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    TrainingRepository trainingRepository;

    List<String> errorMessages = new ArrayList<String>();
    ResourceNotFoundException notFoundException;

    @GetMapping("")
    public Iterable<Certificate> certificateList(@RequestParam(required = false, value = "search", defaultValue = "") String searchData,
                                                 @RequestParam(required = false, value = "size", defaultValue = "5") int size,
                                                 @RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                 @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort)
            throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }
            return certificateServiceImpl.findAllCertificate(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object certificateDetail(@PathVariable("id") Long id) {
        if (certificateServiceImpl.findCertificateById(id) == null) {
            return new ResourceNotFoundException("Sertifikat", "ID", id);
        }
        return certificateServiceImpl.findCertificateById(id);
    }

    @PostMapping("/")
    public ResponseEntity<CertificateResponse<Certificate>> certificateCreate(@Valid @RequestBody CertificateRequest certificateRequest,
                                                                              Errors errors) throws IOException {
        try {
            CertificateResponse<Certificate> responseData = new CertificateResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            ParticipantInternal participantInternal = participantInternalRepository.findParticipantInternalById(certificateRequest.getIdPeserta());
            Trainer trainer = trainerRepository.findTrainerById(certificateRequest.getIdTrainer());
            Training training = trainingRepository.findTrainingById(certificateRequest.getIdPelatihan());

            if (participantInternal == null) {
                notFoundException = new ResourceNotFoundException("Peserta", "ID", certificateRequest.getIdPeserta());
                errorMessages.add(notFoundException.getMessage());
                responseData.setData(null);
                responseData.setStatus(false);
                responseData.setCode(404);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (trainer == null) {
                notFoundException = new ResourceNotFoundException("Trainer", "ID", certificateRequest.getIdTrainer());
                errorMessages.add(notFoundException.getMessage());
                responseData.setData(null);
                responseData.setStatus(false);
                responseData.setCode(404);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (training == null) {
                notFoundException = new ResourceNotFoundException("Training", "ID", certificateRequest.getIdPelatihan());
                errorMessages.add(notFoundException.getMessage());
                responseData.setData(null);
                responseData.setStatus(false);
                responseData.setCode(404);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }

            Certificate certificate = new Certificate(certificateRequest.getKodeSertifikat(), certificateRequest.getTanggalMulaiPelatihan(),
                    certificateRequest.getTanggalSelesaiPelatihan(), certificateRequest.getTanggalExpired(), certificateRequest.getNamaLembaga(),
                    certificateRequest.getLokasi(), certificateRequest.getFileSertifikat(), certificateRequest.getIdPeserta(),
                    certificateRequest.getIdTrainer(), certificateRequest.getIdPelatihan(), certificateRequest.getCreatedAt(),
                    certificateRequest.getUpdatedAt());

            certificate.setPeserta(participantInternal);
            certificate.setTrainer(trainer);
            certificate.setPelatihan(training);

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(certificateServiceImpl.createCertificate(certificate));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateResponse<Certificate>> certificateUpdate(@Valid @RequestBody CertificateRequest certificateRequest,
                                                                              Errors errors, @PathVariable("id") Long id)
            throws IOException {
        try {
            CertificateResponse<Certificate> responseData = new CertificateResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            ParticipantInternal participantInternal = participantInternalRepository.findParticipantInternalById(certificateRequest.getIdPeserta());
            Trainer trainer = trainerRepository.findTrainerById(certificateRequest.getIdTrainer());
            Training training = trainingRepository.findTrainingById(certificateRequest.getIdPelatihan());

            if (participantInternal == null) {
                notFoundException = new ResourceNotFoundException("Peserta", "ID", certificateRequest.getIdPeserta());
                errorMessages.add(notFoundException.getMessage());
                responseData.setData(null);
                responseData.setStatus(false);
                responseData.setCode(404);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (trainer == null) {
                notFoundException = new ResourceNotFoundException("Trainer", "ID", certificateRequest.getIdTrainer());
                errorMessages.add(notFoundException.getMessage());
                responseData.setData(null);
                responseData.setStatus(false);
                responseData.setCode(404);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (training == null) {
                notFoundException = new ResourceNotFoundException("Training", "ID", certificateRequest.getIdPelatihan());
                errorMessages.add(notFoundException.getMessage());
                responseData.setData(null);
                responseData.setStatus(false);
                responseData.setCode(404);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }

            Certificate certificate = new Certificate(certificateRequest.getKodeSertifikat(), certificateRequest.getTanggalMulaiPelatihan(),
                    certificateRequest.getTanggalSelesaiPelatihan(), certificateRequest.getTanggalExpired(), certificateRequest.getNamaLembaga(),
                    certificateRequest.getLokasi(), certificateRequest.getFileSertifikat(), certificateRequest.getIdPeserta(),
                    certificateRequest.getIdTrainer(), certificateRequest.getIdPelatihan(), certificateRequest.getCreatedAt(),
                    certificateRequest.getUpdatedAt());

            certificate.setPeserta(participantInternal);
            certificate.setTrainer(trainer);
            certificate.setPelatihan(training);

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(certificateServiceImpl.updateCertificate(id, certificate));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Certificate certificateDelete(@PathVariable("id") Long id) {
        return certificateServiceImpl.deleteCertificate(id);
    }

}
