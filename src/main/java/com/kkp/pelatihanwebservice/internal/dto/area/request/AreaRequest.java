package com.kkp.pelatihanwebservice.internal.dto.area.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class AreaRequest {
    @NotEmpty(message = "Nama wilayah tidak boleh kosong")
    private String namaWilayah;

    @Email(message = "Invalid email")
    private String email1;

    @Email(message = "Invalid email")
    private String email2;

    @Email(message = "Invalid email")
    private String email3;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public String getNamaWilayah() {
        return namaWilayah;
    }

    public void setNamaWilayah(String namaWilayah) {
        this.namaWilayah = namaWilayah;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
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
