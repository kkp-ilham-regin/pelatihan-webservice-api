package com.kkp.pelatihanwebservice.internal.dto.participant.response;

import com.kkp.pelatihanwebservice.internal.models.ParticipantInternal;

import java.util.ArrayList;
import java.util.List;

public class ParticipantResponse<T> {
    private int code;
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private ParticipantInternal data;

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

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public ParticipantInternal getData() {
        return data;
    }

    public void setData(ParticipantInternal data) {
        this.data = data;
    }
}
