package com.kkp.pelatihanwebservice.internal.dto.area.response;

import com.kkp.pelatihanwebservice.internal.models.Area;

import java.util.ArrayList;
import java.util.List;

public class AreaResponse<T> {
    private int code;
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private Area data;

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

    public Area getData() {
        return data;
    }

    public void setData(Area data) {
        this.data = data;
    }
}
