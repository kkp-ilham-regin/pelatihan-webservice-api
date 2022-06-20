package com.kkp.pelatihanwebservice.internal.dto.education.request;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class EducationRequest {
    @NotEmpty(message = "Kode tidak boleh kosong")
    private String kode;

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String nama;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
