package com.kkp.pelatihanwebservice.internal.dto.employeeStatus.response;

import com.kkp.pelatihanwebservice.internal.models.EmployeeStatus;

import java.util.ArrayList;
import java.util.List;

public class EmployeeStatusResponse<T> {
    private int code;
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private EmployeeStatus data;

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

    public EmployeeStatus getData() {
        return data;
    }

    public void setData(EmployeeStatus data) {
        this.data = data;
    }
}
