package com.kkp.pelatihanwebservice.internal.dto.employee.response;

import com.kkp.pelatihanwebservice.internal.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeResponse<T> {
    private int code;
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private Employee data;

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

    public Employee getData() {
        return data;
    }

    public void setData(Employee data) {
        this.data = data;
    }
}
