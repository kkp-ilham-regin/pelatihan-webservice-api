package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.participant.response.ParticipantImportExcelResponseMessage;
import com.kkp.pelatihanwebservice.internal.helpers.ExcelParticipantHelper;
import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;
import com.kkp.pelatihanwebservice.internal.services.participantInternal.ParticipantInternalExcelService;
import com.kkp.pelatihanwebservice.internal.services.participantInternal.ParticipantInternalService;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1/participants")
public class ParticipantInternalController {
    @Autowired
    ParticipantInternalService participantInternalService;

    @Autowired
    ParticipantInternalExcelService excelService;

    public ParticipantInternalController(ParticipantInternalService participantInternalService) {
        this.participantInternalService = participantInternalService;
    }

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
            return participantInternalService.findAllParticipantInternal(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object participantInternalDetail(@PathVariable("id") Long id) {
        if (participantInternalService.findParticipantInternalById(id) == null) {
            return new ResourceNotFoundException("Peserta", "ID", id);
        }
        return participantInternalService.findParticipantInternalById(id);
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
}
