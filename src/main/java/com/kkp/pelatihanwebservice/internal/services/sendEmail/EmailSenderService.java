package com.kkp.pelatihanwebservice.internal.services.sendEmail;

public interface EmailSenderService {

    void sendEmail(String to, String subject, String message);
}
