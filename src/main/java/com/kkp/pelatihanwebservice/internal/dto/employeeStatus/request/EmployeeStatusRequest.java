package com.kkp.pelatihanwebservice.internal.dto.employeeStatus.request;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class EmployeeStatusRequest {
    @NotEmpty(message = "Nama status tidak boleh kosong")
    private String namaStatus;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public String getNamaStatus() {
        return namaStatus;
    }

    public void setNamaStatus(String namaStatus) {
        this.namaStatus = namaStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
