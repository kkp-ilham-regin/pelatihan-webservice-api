package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.maritalStatus.request.MaritalStatusRequest;
import com.kkp.pelatihanwebservice.internal.dto.maritalStatus.response.MaritalStatusResponse;
import com.kkp.pelatihanwebservice.internal.models.MaritalStatus;
import com.kkp.pelatihanwebservice.internal.repositories.MaritalStatusRepository;
import com.kkp.pelatihanwebservice.internal.services.maritalStatus.MaritalStatusService;
import com.kkp.pelatihanwebservice.internal.services.maritalStatus.MaritalStatusServiceImpl;
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
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1/marital-statuses")
public class MaritalStatusController {

    private MaritalStatusService maritalStatusService;

    @Autowired
    private MaritalStatusServiceImpl maritalStatusServiceImpl;

    MaritalStatusRepository maritalStatusRepository;

    public MaritalStatusController(MaritalStatusService maritalStatusService) {
        this.maritalStatusService = maritalStatusService;
    }

    @GetMapping("")
    public Iterable<MaritalStatus> maritalStatusList(
            @RequestParam(required = false, name = "search", defaultValue = "") String searchData,
            @RequestParam(required = false, name = "size", defaultValue = "") int size,
            @RequestParam(required = false, name = "page", defaultValue = "") int page,
            @RequestParam(required = false, name = "sort", defaultValue = "") String sort) throws IOException {

        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").descending());
            }

            return maritalStatusServiceImpl.getAllMaritalStatus(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }

    }

    @GetMapping("/{id}")
    public Object maritalStatusDetail(@PathVariable("id") Long id) {
        if (maritalStatusServiceImpl.getMaritalStatusDetail(id) == null) {
            return new ResourceNotFoundException("Status Pernikahan", "ID", id);
        }
        return maritalStatusServiceImpl.getMaritalStatusDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<MaritalStatusResponse<MaritalStatus>> createMaritalStatus(
            @Valid @RequestBody MaritalStatusRequest maritalStatusRequest,
            Errors errors) throws IOException {
        try {
            MaritalStatusResponse<MaritalStatus> responseData = new MaritalStatusResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            MaritalStatus maritalStatus = new MaritalStatus(maritalStatusRequest.getKode(), maritalStatusRequest.getNamaStatusPernikahan(),
                    maritalStatusRequest.getCreadtedAt(), maritalStatusRequest.getUpdatedAt());

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(maritalStatusServiceImpl.createMaritalStatus(maritalStatus));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaritalStatusResponse<MaritalStatus>> updateMaritalStatus(
            @Valid @RequestBody MaritalStatusRequest maritalStatusRequest,
            @PathVariable("id") Long id,
            Errors errors) throws IOException {
        try {
            MaritalStatusResponse<MaritalStatus> responseData = new MaritalStatusResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            MaritalStatus maritalStatus = new MaritalStatus(maritalStatusRequest.getKode(), maritalStatusRequest.getNamaStatusPernikahan(),
                    maritalStatusRequest.getCreadtedAt(), maritalStatusRequest.getUpdatedAt());

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(maritalStatusServiceImpl.updateMaritalStatus(id, maritalStatus));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @DeleteMapping("/{id}")
    public MaritalStatus deleteMaritalStatus(@PathVariable("id") Long id) {
        return maritalStatusServiceImpl.deleteMaritalStatus(id);
    }
}
