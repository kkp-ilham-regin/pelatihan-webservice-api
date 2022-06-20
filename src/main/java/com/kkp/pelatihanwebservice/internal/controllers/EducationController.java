package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.external.utils.exceptions.ResourceNotFoundException;
import com.kkp.pelatihanwebservice.internal.dto.education.request.EducationRequest;
import com.kkp.pelatihanwebservice.internal.dto.education.response.EducationResponse;
import com.kkp.pelatihanwebservice.internal.models.Education;
import com.kkp.pelatihanwebservice.internal.repositories.EducationRepository;
import com.kkp.pelatihanwebservice.internal.services.education.EducationService;
import com.kkp.pelatihanwebservice.internal.services.education.EducationServiceImpl;
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
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1/educations")
public class EducationController {

    private EducationService educationService;

    @Autowired
    private EducationServiceImpl educationServiceImpl;

    @Autowired
    EducationRepository educationRepository;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("")
    public Iterable<Education> educationList(
            @RequestParam(required = false, name = "search", defaultValue = "") String searchData,
            @RequestParam(required = false, name = "size", defaultValue = "5") int size,
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "sort", defaultValue = "desc") String sort
    ) throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").descending());
            }

            return educationServiceImpl.getAllEducation(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object educationDetail(@PathVariable("id") Long id) {
        if (educationServiceImpl.getEducationDetail(id) == null) {
            return new ResourceNotFoundException("Pendidikan", "ID", id);
        }
        return educationServiceImpl.getEducationDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<EducationResponse<Education>> createEducation(
            @Valid @RequestBody EducationRequest educationRequest,
            Errors errors) throws IOException {
        try {
            EducationResponse<Education> responseData = new EducationResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Education education = new Education(educationRequest.getKode(), educationRequest.getNama(),
                    educationRequest.getCreatedAt(), educationRequest.getUpdatedAt());

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(educationServiceImpl.createEducation(education));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationResponse<Education>> updateEducation(
            @Valid @RequestBody EducationRequest educationRequest,
            @PathVariable("id") Long id,
            Errors errors) throws IOException {
        try {
            EducationResponse<Education> responseData = new EducationResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }

                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Education education = new Education(educationRequest.getKode(), educationRequest.getNama(),
                    educationRequest.getCreatedAt(), educationRequest.getUpdatedAt());

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(educationServiceImpl.updateEducation(id, education));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Education deleteEducation(@PathVariable("id") Long id) {
        return educationServiceImpl.deleteEducation(id);
    }
}
