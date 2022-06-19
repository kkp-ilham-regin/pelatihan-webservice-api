package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.religion.request.ReligionRequest;
import com.kkp.pelatihanwebservice.internal.dto.religion.response.ReligionResponse;
import com.kkp.pelatihanwebservice.internal.models.Religion;
import com.kkp.pelatihanwebservice.internal.repositories.ReligionRepository;
import com.kkp.pelatihanwebservice.internal.services.religion.ReligionService;
import com.kkp.pelatihanwebservice.internal.services.religion.ReligionServiceImpl;
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
@RequestMapping("/pelatihan-webservice/internal-api/v1/religions")
public class ReligionController {

    private ReligionService religionService;

    @Autowired
    private ReligionServiceImpl religionServiceImpl;

    @Autowired
    ReligionRepository religionRepository;

    public ReligionController(ReligionService religionService) {
        this.religionService = religionService;
    }

    @GetMapping("")
    public Iterable<Religion> religionList(
            @RequestParam(required = false, value = "search", defaultValue = "") String searchData,
            @RequestParam(required = false, value = "size", defaultValue = "5") int size,
            @RequestParam(required = false, value = "page", defaultValue = "0") int page,
            @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort
    ) throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            if (sort.equalsIgnoreCase("asc"))
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            return religionServiceImpl.findAllReligion(searchData, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object getReligionDetail(@PathVariable("id") Long id) {
        if (religionServiceImpl.getReligionDetail(id) == null) {
            return new ResourceNotFoundException("Agama", "ID", id);
        }
        return religionServiceImpl.getReligionDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<ReligionResponse<Religion>> createReligion(@Valid @RequestBody ReligionRequest religionRequest,
                                                                     Errors errors) throws IOException {
        try {
            ReligionResponse<Religion> responseData = new ReligionResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }

                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Religion religion = new Religion(religionRequest.getKode(), religionRequest.getNama(), religionRequest.getCreatedAt()
                    , religionRequest.getUpdateAt());

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(religionServiceImpl.createReligion(religion));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReligionResponse<Religion>> updateReligion(
            @PathVariable("id") Long id,
            @Valid @RequestBody ReligionRequest religionRequest,
            Errors errors
    ) throws IOException {
        try {
            ReligionResponse<Religion> responseData = new ReligionResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }

                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Religion religion = new Religion(religionRequest.getKode(), religionRequest.getNama(), religionRequest.getCreatedAt()
                    , religionRequest.getUpdateAt());

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(religionServiceImpl.updateReligion(id, religion));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Religion deleteReligion(@PathVariable("id") Long id) {
        return religionServiceImpl.deleteReligion(id);
    }
}
