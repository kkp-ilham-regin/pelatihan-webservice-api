package com.kkp.pelatihanwebservice.internal.dto.participant.response;

public class ParticipantImportExcelResponseMessage {
    private String message;
    private int code;
    private boolean status;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public ParticipantImportExcelResponseMessage(int code, boolean status, String message, String errorMessage) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
