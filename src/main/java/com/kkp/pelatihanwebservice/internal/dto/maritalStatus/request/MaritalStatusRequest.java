package com.kkp.pelatihanwebservice.internal.dto.maritalStatus.request;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class MaritalStatusRequest {
    @NotEmpty(message = "Kode tidak boleh kosong")
    private String kode;

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String namaStatusPernikahan;

    private LocalDateTime creadtedAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaStatusPernikahan() {
        return namaStatusPernikahan;
    }

    public void setNamaStatusPernikahan(String namaStatusPernikahan) {
        this.namaStatusPernikahan = namaStatusPernikahan;
    }

    public LocalDateTime getCreadtedAt() {
        return creadtedAt;
    }

    public void setCreadtedAt(LocalDateTime creadtedAt) {
        this.creadtedAt = creadtedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
