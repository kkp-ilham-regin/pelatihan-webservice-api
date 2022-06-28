package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.offer.request.OfferRequest;
import com.kkp.pelatihanwebservice.internal.dto.offer.request.UpdateStatusOfferRequest;
import com.kkp.pelatihanwebservice.internal.dto.offer.response.OfferResponse;
import com.kkp.pelatihanwebservice.internal.models.Offer;
import com.kkp.pelatihanwebservice.internal.models.Status;
import com.kkp.pelatihanwebservice.internal.models.Training;
import com.kkp.pelatihanwebservice.internal.models.UserApi;
import com.kkp.pelatihanwebservice.internal.repositories.OfferRepository;
import com.kkp.pelatihanwebservice.internal.repositories.StatusRepository;
import com.kkp.pelatihanwebservice.internal.repositories.TrainingRepository;
import com.kkp.pelatihanwebservice.internal.repositories.UserApiRepository;
import com.kkp.pelatihanwebservice.internal.services.offer.OfferService;
import com.kkp.pelatihanwebservice.internal.services.offer.OfferServiceImpl;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pelatihan-webservice/internal-api/v1/offers")
public class OfferController {

    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @Autowired
    OfferServiceImpl offerServiceImpl;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    UserApiRepository userApiRepository;

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    StatusRepository statusRepository;

    List<String> errorMessages = new ArrayList<>();
    ResourceNotFoundException notFoundException;

    // public user side
    @GetMapping("")
    public Iterable<Offer> offerList(
            @RequestParam(value = "userId", defaultValue = "0") int userId,
            @RequestParam(required = false, value = "size", defaultValue = "5") int size,
            @RequestParam(required = false, value = "page", defaultValue = "0") int page,
            @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort
    ) throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            Long parseUserId = Long.valueOf(userId);
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }
            return offerServiceImpl.findAllOfferByUserId(parseUserId, pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/{id}")
    public Object offerDetail(@PathVariable("id") Long id) {
        if (offerServiceImpl.getOfferDetail(id) == null) {
            return new ResourceNotFoundException("Penawaran", "ID", id);
        }
        return offerServiceImpl.getOfferDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<OfferResponse<Offer>> offerCreate(
            @Valid @RequestBody OfferRequest offerRequest,
            Errors errors
    ) throws IOException {
        try {
            OfferResponse<Offer> responseData = new OfferResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            UserApi user = userApiRepository.findUserApiById(offerRequest.getUserId());
            Training training = trainingRepository.findTrainingById(offerRequest.getPelatihanId());
            Status status = statusRepository.findStatusById(offerRequest.getStatusId());

            if (user == null) {
                notFoundException = new ResourceNotFoundException("User", "ID", offerRequest.getUserId());
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
                notFoundException = new ResourceNotFoundException("Pelatihan", "ID", offerRequest.getPelatihanId());
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

            if (status == null) {
                notFoundException = new ResourceNotFoundException("Status", "ID", offerRequest.getStatusId());
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

            Offer offer = new Offer(offerRequest.getNamaPerusahaan(), offerRequest.getNamaPic(), offerRequest.getTanggalPelatihan(),
                    offerRequest.getTempatPelatihan(), offerRequest.getEmail(), offerRequest.getNoTelp(), offerRequest.getAlamatKantor(),
                    offerRequest.getUserId(), offerRequest.getPelatihanId(), offerRequest.getStatusId(), offerRequest.getCreatedAt(),
                    offerRequest.getUpdatedAt());
            offer.setUser(user);
            offer.setStatus(status);
            offer.setTraining(training);

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(offerServiceImpl.createOffer(offer));

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    // admin side
    @GetMapping("/admin")
    public Iterable<Offer> offerList(
            @RequestParam(required = false, value = "size", defaultValue = "5") int size,
            @RequestParam(required = false, value = "page", defaultValue = "0") int page,
            @RequestParam(required = false, value = "sort", defaultValue = "desc") String sort
    ) throws IOException {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            if (sort.equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            }
            return offerServiceImpl.findAllOffer(pageable);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferResponse<Offer>> updateStatusOffer(
            @Valid @RequestBody UpdateStatusOfferRequest offerRequest,
            Errors errors,
            @PathVariable("id") Long id
    ) throws IOException {
        try {
            OfferResponse<Offer> responseData = new OfferResponse<>();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            Status status = statusRepository.findStatusById(offerRequest.getStatusId());

            if (status == null) {
                notFoundException = new ResourceNotFoundException("Status", "ID", offerRequest.getStatusId());
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

            Offer offer = new Offer(offerRequest.getStatusId(), offerRequest.getUpdatedAt());
            offer.setStatus(status);

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setData(offerServiceImpl.approveOrRejectOffer(id, offer));


            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
