package com.kkp.pelatihanwebservice.internal.dto.publicUser.response;

import com.kkp.pelatihanwebservice.internal.models.UserApi;

import java.util.ArrayList;
import java.util.List;

public class PublicUserResponse<T> {
    private int code;
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private UserApi data;

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

    public UserApi getData() {
        return data;
    }

    public void setData(UserApi data) {
        this.data = data;
    }
}
