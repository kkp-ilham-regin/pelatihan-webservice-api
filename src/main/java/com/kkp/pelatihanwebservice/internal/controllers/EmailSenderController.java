package com.kkp.pelatihanwebservice.internal.controllers;

import com.kkp.pelatihanwebservice.internal.dto.sendEmail.request.EmailSenderRequest;
import com.kkp.pelatihanwebservice.internal.dto.sendEmail.response.EmailSenderResponse;
import com.kkp.pelatihanwebservice.internal.services.sendEmail.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/pelatihan-webservice/internal-api/v1")
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<EmailSenderResponse> sendEmail(@Valid @RequestBody EmailSenderRequest request, Errors errors)
            throws IOException {
        try {
            EmailSenderResponse responseData = new EmailSenderResponse();

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getErrorMessages().add(error.getDefaultMessage());
                }
                responseData.setStatus(false);
                responseData.setCode(400);
                responseData.setMessage("");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            responseData.setStatus(true);
            responseData.setCode(200);
            responseData.setMessage("Email terkirim ke " + request.getTo());
            emailSenderService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            throw new IOException(e);
        }

    }

}
