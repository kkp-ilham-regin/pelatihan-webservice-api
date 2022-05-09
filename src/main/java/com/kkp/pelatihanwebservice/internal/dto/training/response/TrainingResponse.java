package com.kkp.pelatihanwebservice.internal.dto.training.response;

import com.kkp.pelatihanwebservice.internal.models.Training;

import java.util.ArrayList;
import java.util.List;

public class TrainingResponse<T> {
    private int code;
    private boolean status;
    private List<String> messages = new ArrayList<>();
    Training data;

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

    public Training getData() {
        return data;
    }

    public void setData(Training data) {
        this.data = data;
    }
}
