package com.kkp.pelatihanwebservice.internal.dto.training.request;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class TrainingRequest {
    @NotEmpty(message = "Kode tidak boleh kosong")
    private String kode;

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String nama;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = null;

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
