package com.kkp.pelatihanwebservice.internal.dto.offer.response;

import com.kkp.pelatihanwebservice.internal.models.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferResponse<T> {
    private int code;
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private Offer data;

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

    public Offer getData() {
        return data;
    }

    public void setData(Offer data) {
        this.data = data;
    }
}
