package com.kkp.pelatihanwebservice.internal.dto.sendEmail.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class EmailSenderRequest {
    @NotBlank(message = "Email penerima tidak boleh kosong")
    @NotEmpty(message = "Email penerima tidak boleh kosong")
    @Email(message = "Email tidak valid")
    private String to;

    @NotBlank(message = "Subject tidak boleh kosong")
    @NotEmpty(message = "Subject tidak boleh kosong")
    private String subject;

    @NotBlank(message = "Message tidak boleh kosong")
    @NotEmpty(message = "Message tidak boleh kosong")
    private String message;

    public EmailSenderRequest() {
    }

    public EmailSenderRequest(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
