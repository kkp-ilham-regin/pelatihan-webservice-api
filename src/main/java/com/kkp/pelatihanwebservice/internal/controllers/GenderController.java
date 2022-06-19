package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.gender.request.GenderRequest;
import com.kkp.pelatihanwebservice.internal.dto.gender.response.GenderResponse;
import com.kkp.pelatihanwebservice.internal.models.Gender;
import com.kkp.pelatihanwebservice.internal.repositories.GenderRepository;
import com.kkp.pelatihanwebservice.internal.services.gender.GenderService;
import com.kkp.pelatihanwebservice.internal.services.gender.GenderServiceImpl;
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
@RequestMapping("/pelatihan-webservice/internal-api/v1/genders")
public class GenderController {

    private GenderService genderService;

    @Autowired
    private GenderServiceImpl genderServiceImpl;

    @Autowired
    GenderRepository genderRepository;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping("")
    public Iterable<Gender> genderList(@RequestParam(required = false, value = "search", defaultValue = "") String searchData,
                                       @RequestParam(required = false, value = "size", defaultValue = "5") int size,
                                       @RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                       @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort)
            throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }
            return genderServiceImpl.findAllGender(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object genderDetail(@PathVariable("id") Long id) {
        if (genderServiceImpl.getGenderDetail(id) == null) {
            return new ResourceNotFoundException("Jenis Kelamin", "ID", id);
        }
        return genderServiceImpl.getGenderDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<GenderResponse<Gender>> genderCreate(@Valid @RequestBody GenderRequest genderRequest,
                                                               Errors errors) throws IOException {
        try {
            GenderResponse<Gender> responseData = new GenderResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Gender gender = new Gender(genderRequest.getKode(), genderRequest.getNama(), genderRequest.getCreatedAt(),
                    genderRequest.getUpdatedAt());
            responseData.setStatus(true);
            responseData.setData(genderServiceImpl.createGender(gender));
            responseData.setCode(200);

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenderResponse<Gender>> genderUpdate(@Valid @RequestBody GenderRequest genderRequest,
                                                               @PathVariable("id") Long id, Errors errors)
            throws IOException {
        try {
            GenderResponse<Gender> responseData = new GenderResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Gender gender = new Gender(genderRequest.getKode(), genderRequest.getNama(), genderRequest.getCreatedAt(),
                    genderRequest.getUpdatedAt());
            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(genderServiceImpl.updateGender(id, gender));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Gender genderDelete(@PathVariable("id") Long id) {
        return genderServiceImpl.deleteGender(id);
    }
}
