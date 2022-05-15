package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;
import com.kkp.pelatihanwebservice.internal.services.participantInternal.ParticipantInternalService;
import com.kkp.pelatihanwebservice.internal.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1/participants")
public class ParticipantInternalController {
    @Autowired
    ParticipantInternalService participantInternalService;

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
}
