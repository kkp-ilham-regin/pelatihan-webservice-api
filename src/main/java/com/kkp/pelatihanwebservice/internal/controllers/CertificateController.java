package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.certificate.request.CertificateRequest;
import com.kkp.pelatihanwebservice.internal.dto.certificate.response.CertificateResponse;
import com.kkp.pelatihanwebservice.internal.models.*;
import com.kkp.pelatihanwebservice.internal.repositories.*;
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
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    OfferRepository offerRepository;

    List<String> errorMessages = new ArrayList<String>();
    ResourceNotFoundException notFoundException;

    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generateCertificateCode(int length) {
        String result = " - ";
        int charsLength = characters.length();
        for (int i = 0; i < length; i++) {
            result += characters.charAt((int) Math.floor(Math.random() * charsLength));
        }
        return result;
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

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

    // TODO: will be used only on admin or deprecated endpoint
//    @GetMapping("/{id}")
//    public Object certificateDetail(@PathVariable("id") Long id) {
//        if (certificateServiceImpl.findCertificateById(id) == null) {
//            return new ResourceNotFoundException("Sertifikat", "ID", id);
//        }
//        return certificateServiceImpl.findCertificateById(id);
//    }

    @GetMapping("/{participantId}/{offerId}")
    public Object certificateDetail(
            @PathVariable("participantId") Long participantId,
            @PathVariable("offerId") Long offerId
    ) {
        CertificateResponse<Certificate> responseData = new CertificateResponse<>();
        Certificate certificate = certificateServiceImpl.findParticipantCertificateBelongstoOfferDetail(participantId, offerId);
        if (certificate == null) {
            String errMsg = "Sertifikat dari participant id " + participantId + " dan penawaran id " + offerId + " tidak " +
                    "ditemukan";
            errorMessages.add(errMsg);
            responseData.setData(null);
            responseData.setStatus(false);
            responseData.setCode(404);
            for (String message : errorMessages) {
                responseData.getMessages().add(message);
            }
            errorMessages.clear();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        return certificate;
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


            Certificate existingCertificate = certificateRepository.findCertificateByIdPesertaAndIdPenawaran(certificateRequest.getIdPeserta(),
                    certificateRequest.getIdPenawaran());

            // handling can't create certicate twice for the same user and offer before
            if (existingCertificate != null) {
                String errMessage = "Sertifikat telah dibuat sebelumnya";
                errorMessages.add(errMessage);
                responseData.setData(null);
                responseData.setStatus(false);
                responseData.setCode(409);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }

            Offer offer = offerRepository.findOfferById(certificateRequest.getIdPenawaran());
            Calendar cal = dateToCalendar(offer.getTanggalPelatihan());
            cal.add(Calendar.MONTH, +24);
            Date result = cal.getTime();

            ParticipantInternal participantInternal = participantInternalRepository.findParticipantInternalById(certificateRequest.getIdPeserta());

            if (offer == null) {
                notFoundException = new ResourceNotFoundException("Penawaran", "ID", certificateRequest.getIdPenawaran());
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

            Long trainerId = offer.getTrainerId();
            Long trainingId = offer.getPelatihanId();

            Trainer trainer = trainerRepository.findTrainerById(trainerId);
            Training training = trainingRepository.findTrainingById(trainingId);

            if (trainer == null) {
                notFoundException = new ResourceNotFoundException("Trainer", "ID", trainerId);
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
                notFoundException = new ResourceNotFoundException("Training", "ID", trainingId);
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

            String instituteName = offer.getNamaPerusahaan();
            String certificateCode = instituteName.concat(generateCertificateCode(5));
            Date trainingStartDate = offer.getTanggalPelatihan();
            Date trainingEndDate = offer.getTanggalPelatihan();
            Date certificateExpired = result;
            String trainingLocation = offer.getAlamatKantor();

            Certificate certificate = new Certificate(certificateCode, trainingStartDate,
                    trainingEndDate, certificateExpired, instituteName,
                    trainingLocation, certificateRequest.getFileSertifikat(), certificateRequest.getIdPeserta(),
                    trainerId, trainingId, certificateRequest.getIdPenawaran(), certificateRequest.getCreatedAt(),
                    certificateRequest.getUpdatedAt());

            certificate.setPenawaran(offer);
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


    // TODO: deprecated endpoint
//    @PutMapping("/{id}")
//    public ResponseEntity<CertificateResponse<Certificate>> certificateUpdate(@Valid @RequestBody CertificateRequest certificateRequest,
//                                                                              Errors errors, @PathVariable("id") Long id)
//            throws IOException {
//        try {
//            CertificateResponse<Certificate> responseData = new CertificateResponse<>();
//
//            if (errors.hasErrors()) {
//                for (ObjectError error : errors.getAllErrors()) {
//                    responseData.getMessages().add(error.getDefaultMessage());
//                }
//                responseData.setStatus(false);
//                responseData.setCode(400);
//                responseData.setData(null);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
//            }
//
//            ParticipantInternal participantInternal = participantInternalRepository.findParticipantInternalById(certificateRequest.getIdPeserta());
//            Trainer trainer = trainerRepository.findTrainerById(certificateRequest.getIdTrainer());
//            Training training = trainingRepository.findTrainingById(certificateRequest.getIdPelatihan());
//
//            if (participantInternal == null) {
//                notFoundException = new ResourceNotFoundException("Peserta", "ID", certificateRequest.getIdPeserta());
//                errorMessages.add(notFoundException.getMessage());
//                responseData.setData(null);
//                responseData.setStatus(false);
//                responseData.setCode(404);
//                for (String message : errorMessages) {
//                    responseData.getMessages().add(message);
//                }
//                errorMessages.clear();
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
//            }
//            if (trainer == null) {
//                notFoundException = new ResourceNotFoundException("Trainer", "ID", certificateRequest.getIdTrainer());
//                errorMessages.add(notFoundException.getMessage());
//                responseData.setData(null);
//                responseData.setStatus(false);
//                responseData.setCode(404);
//                for (String message : errorMessages) {
//                    responseData.getMessages().add(message);
//                }
//                errorMessages.clear();
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
//            }
//            if (training == null) {
//                notFoundException = new ResourceNotFoundException("Training", "ID", certificateRequest.getIdPelatihan());
//                errorMessages.add(notFoundException.getMessage());
//                responseData.setData(null);
//                responseData.setStatus(false);
//                responseData.setCode(404);
//                for (String message : errorMessages) {
//                    responseData.getMessages().add(message);
//                }
//                errorMessages.clear();
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
//            }
//
//            Certificate certificate = new Certificate(certificateRequest.getKodeSertifikat(), certificateRequest.getTanggalMulaiPelatihan(),
//                    certificateRequest.getTanggalSelesaiPelatihan(), certificateRequest.getTanggalExpired(), certificateRequest.getNamaLembaga(),
//                    certificateRequest.getLokasi(), certificateRequest.getFileSertifikat(), certificateRequest.getIdPeserta(),
//                    certificateRequest.getIdTrainer(), certificateRequest.getIdPelatihan(), certificateRequest.getCreatedAt(),
//                    certificateRequest.getUpdatedAt());
//
//            certificate.setPeserta(participantInternal);
//            certificate.setTrainer(trainer);
//            certificate.setPelatihan(training);
//
//            responseData.setStatus(true);
//            responseData.setCode(200);
//            responseData.setData(certificateServiceImpl.updateCertificate(id, certificate));
//            return ResponseEntity.ok(responseData);
//        } catch (Exception e) {
//            throw new IOException(e);
//        }
//    }

    // TODO: deprecated endpoint
//    @DeleteMapping("/{id}")
//    public Certificate certificateDelete(@PathVariable("id") Long id) {
//        return certificateServiceImpl.deleteCertificate(id);
//    }

}
