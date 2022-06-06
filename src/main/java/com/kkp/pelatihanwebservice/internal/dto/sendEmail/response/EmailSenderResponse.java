package com.kkp.pelatihanwebservice.internal.dto.sendEmail.response;

import java.util.ArrayList;
import java.util.List;

public class EmailSenderResponse<T> {
    private int code;
    private boolean status;
    private List<String> errorMessages = new ArrayList<>();
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
