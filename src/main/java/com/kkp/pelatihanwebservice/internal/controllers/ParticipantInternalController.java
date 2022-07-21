package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.participant.request.ParticipantRequest;
import com.kkp.pelatihanwebservice.internal.dto.participant.response.ParticipantImportExcelResponseMessage;
import com.kkp.pelatihanwebservice.internal.dto.participant.response.ParticipantResponse;
import com.kkp.pelatihanwebservice.internal.helpers.ExcelParticipantHelper;
import com.kkp.pelatihanwebservice.internal.models.*;
import com.kkp.pelatihanwebservice.internal.repositories.*;
import com.kkp.pelatihanwebservice.internal.services.participantInternal.ParticipantInternalExcelService;
import com.kkp.pelatihanwebservice.internal.services.participantInternal.ParticipantInternalServiceImpl;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1/participants")
public class ParticipantInternalController {
    @Autowired
    ParticipantInternalServiceImpl participantInternalServiceImpl;

    @Autowired
    ParticipantInternalExcelService excelService;

    public ParticipantInternalController(ParticipantInternalServiceImpl participantInternalServiceImpl) {
        this.participantInternalServiceImpl = participantInternalServiceImpl;
    }

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    ReligionRepository religionRepository;

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    EmployeeStatusRepository employeeStatusRepository;

    @Autowired
    OfferRepository offerRepository;

    List<String> errorMessages = new ArrayList<String>();
    ResourceNotFoundException notFoundException;
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updatedAt = LocalDateTime.now();

    @GetMapping("")
    public Iterable<ParticipantInternal> participantInternalList(@RequestParam(required = false, value = "search", defaultValue = "") String searchData,
                                                                 @RequestParam(required = false, value = "size", defaultValue = "5") int size,
                                                                 @RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort
    ) throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }
            return participantInternalServiceImpl.findAllParticipantInternal(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/offer")
    public Iterable<ParticipantInternal> getAllParticipantByOfferId(
            @RequestParam(value = "offerId", defaultValue = "0") int offerId,
            @RequestParam(required = false, value = "size", defaultValue = "5") int size,
            @RequestParam(required = false, value = "page", defaultValue = "0") int page,
            @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort
    ) throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            Long parseOfferId = Long.valueOf(offerId);
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }
            return participantInternalServiceImpl.findAllParticipantByOfferId(parseOfferId, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object participantInternalDetail(@PathVariable("id") Long id) {
        if (participantInternalServiceImpl.findParticipantInternalById(id) == null) {
            return new ResourceNotFoundException("Peserta", "ID", id);
        }
        return participantInternalServiceImpl.findParticipantInternalById(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<ParticipantImportExcelResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message;
        if (ExcelParticipantHelper.hasExcelFormat(file)) {
            try {
                excelService.save(file);
                message = "Upload file berhasil: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ParticipantImportExcelResponseMessage(200, true, message, ""));
            } catch (Exception e) {
                message = "Gagal upload file: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ParticipantImportExcelResponseMessage(417, false, message, e.getMessage()));
            }
        }
        message = "Please upload an excel file";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ParticipantImportExcelResponseMessage(401, false, message, ""));
    }

    @PostMapping("/")
    public ResponseEntity<ParticipantResponse<ParticipantInternal>> createParticipantInternal(
            @Valid @RequestBody ParticipantRequest participantRequest,
            Errors errors
    ) throws IOException {
        try {
            ParticipantResponse<ParticipantInternal> responseData = new ParticipantResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setCode(400);
                responseData.setStatus(false);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Area area = areaRepository.findAreaById(participantRequest.getIdWilayah());
            Gender gender = genderRepository.findGenderById(participantRequest.getIdJenisKelamin());
            Religion religion = religionRepository.findReligionById(participantRequest.getIdAgama());
            MaritalStatus maritalStatus = maritalStatusRepository.findMaritalStatusById(participantRequest.getIdStatusPernikahan());
            Education education = educationRepository.findEducationById(participantRequest.getIdPendidikan());
            EmployeeStatus employeeStatus = employeeStatusRepository.findEmployeeStatusById(participantRequest.getIdStatusPegawai());
            Offer offer = offerRepository.findOfferById(participantRequest.getIdPenawaran());

            if (offer == null) {
                notFoundException = new ResourceNotFoundException("Penawaran", "ID", participantRequest.getIdPenawaran());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (area == null) {
                notFoundException = new ResourceNotFoundException("Wilayah", "ID", participantRequest.getIdWilayah());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (gender == null) {
                notFoundException = new ResourceNotFoundException("Jenis Kelamin", "ID", participantRequest.getIdJenisKelamin());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (religion == null) {
                notFoundException = new ResourceNotFoundException("Agama", "ID", participantRequest.getIdAgama());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (maritalStatus == null) {
                notFoundException = new ResourceNotFoundException("Status Pernikahan", "ID", participantRequest.getIdStatusPernikahan());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (education == null) {
                notFoundException = new ResourceNotFoundException("Pendidikan", "ID", participantRequest.getIdPendidikan());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (employeeStatus == null) {
                notFoundException = new ResourceNotFoundException("Status Pegawai", "ID", participantRequest.getIdStatusPegawai());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }

            ParticipantInternal participantInternal = new ParticipantInternal(participantRequest.getNamaPeserta(), participantRequest.getNip(),
                    participantRequest.getNomorKta(), participantRequest.getExpiredKta(), participantRequest.getJabatan(),
                    participantRequest.getLokasiKerja(), participantRequest.getNik(), participantRequest.getTanggalTmtMasuk(),
                    participantRequest.getAlamat(), participantRequest.getTempatLahir(), participantRequest.getTanggalLahir(),
                    participantRequest.getGolonganDarah(), participantRequest.getNomorTelepon(), participantRequest.getNamaSekolah(),
                    participantRequest.getEmail(), participantRequest.getNpwp(), participantRequest.getUrlImage(), participantRequest.getIdWilayah(),
                    participantRequest.getIdJenisKelamin(), participantRequest.getIdAgama(), participantRequest.getIdStatusPernikahan(),
                    participantRequest.getIdPendidikan(), participantRequest.getIdStatusPegawai(), participantRequest.getIdPenawaran(),
                    createdAt, participantRequest.getUpdated());

            participantInternal.setWilayah(area);
            participantInternal.setJenisKelamin(gender);
            participantInternal.setAgama(religion);
            participantInternal.setStatusPernikahan(maritalStatus);
            participantInternal.setPendidikan(education);
            participantInternal.setStatusPegawai(employeeStatus);
            participantInternal.setPenawaran(offer);

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(participantInternalServiceImpl.createParticipantInternal(participantInternal));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantResponse<ParticipantInternal>> updateParticipantInternal(
            @Valid @RequestBody ParticipantRequest participantRequest,
            @PathVariable("id") Long id,
            Errors errors
    ) throws IOException {
        try {
            ParticipantResponse<ParticipantInternal> responseData = new ParticipantResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setCode(400);
                responseData.setStatus(false);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Area area = areaRepository.findAreaById(participantRequest.getIdWilayah());
            Gender gender = genderRepository.findGenderById(participantRequest.getIdJenisKelamin());
            Religion religion = religionRepository.findReligionById(participantRequest.getIdAgama());
            MaritalStatus maritalStatus = maritalStatusRepository.findMaritalStatusById(participantRequest.getIdStatusPernikahan());
            Education education = educationRepository.findEducationById(participantRequest.getIdPendidikan());
            EmployeeStatus employeeStatus = employeeStatusRepository.findEmployeeStatusById(participantRequest.getIdStatusPegawai());
            Offer offer = offerRepository.findOfferById(participantRequest.getIdPenawaran());

            if (offer == null) {
                notFoundException = new ResourceNotFoundException("Penawaran", "ID", participantRequest.getIdPenawaran());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (area == null) {
                notFoundException = new ResourceNotFoundException("Wilayah", "ID", participantRequest.getIdWilayah());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (gender == null) {
                notFoundException = new ResourceNotFoundException("Jenis Kelamin", "ID", participantRequest.getIdJenisKelamin());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (religion == null) {
                notFoundException = new ResourceNotFoundException("Agama", "ID", participantRequest.getIdAgama());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (maritalStatus == null) {
                notFoundException = new ResourceNotFoundException("Status Pernikahan", "ID", participantRequest.getIdStatusPernikahan());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (education == null) {
                notFoundException = new ResourceNotFoundException("Pendidikan", "ID", participantRequest.getIdPendidikan());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            if (employeeStatus == null) {
                notFoundException = new ResourceNotFoundException("Status Pegawai", "ID", participantRequest.getIdStatusPegawai());
                errorMessages.add(notFoundException.getMessage());
                responseData.setCode(404);
                responseData.setStatus(false);
                responseData.setData(null);
                for (String message : errorMessages) {
                    responseData.getMessages().add(message);
                }
                errorMessages.clear();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }

            ParticipantInternal participantInternal = new ParticipantInternal(participantRequest.getNamaPeserta(), participantRequest.getNip(),
                    participantRequest.getNomorKta(), participantRequest.getExpiredKta(), participantRequest.getJabatan(),
                    participantRequest.getLokasiKerja(), participantRequest.getNik(), participantRequest.getTanggalTmtMasuk(),
                    participantRequest.getAlamat(), participantRequest.getTempatLahir(), participantRequest.getTanggalLahir(),
                    participantRequest.getGolonganDarah(), participantRequest.getNomorTelepon(), participantRequest.getNamaSekolah(),
                    participantRequest.getEmail(), participantRequest.getNpwp(), participantRequest.getUrlImage(), participantRequest.getIdWilayah(),
                    participantRequest.getIdJenisKelamin(), participantRequest.getIdAgama(), participantRequest.getIdStatusPernikahan(),
                    participantRequest.getIdPendidikan(), participantRequest.getIdStatusPegawai(), participantRequest.getIdPenawaran(),
                    participantRequest.getCreatedAt(), updatedAt);

            participantInternal.setWilayah(area);
            participantInternal.setJenisKelamin(gender);
            participantInternal.setAgama(religion);
            participantInternal.setStatusPernikahan(maritalStatus);
            participantInternal.setPendidikan(education);
            participantInternal.setStatusPegawai(employeeStatus);
            participantInternal.setPenawaran(offer);

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(participantInternalServiceImpl.updateParticipantInternal(id, participantInternal));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
