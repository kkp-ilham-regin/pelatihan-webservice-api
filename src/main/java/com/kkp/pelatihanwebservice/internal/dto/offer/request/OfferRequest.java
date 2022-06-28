package com.kkp.pelatihanwebservice.internal.dto.offer.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

public class OfferRequest {
    @NotEmpty(message = "Nama perusahaan tidak boleh kosong")
    private String namaPerusahaan;

    @NotEmpty(message = "Nama PIC tidak boleh kosong")
    private String namaPic;

    @NotNull(message = "Tanggal pelatihan tidak boleh kosong")
    private Date tanggalPelatihan;

    @NotEmpty(message = "Tempat pelatihan tidak boleh kosong")
    private String tempatPelatihan;

    @NotEmpty(message = "Email tidak boleh kosong")
    @Email(message = "Email tidak valid")
    private String email;

    @NotEmpty(message = "Nomor telepon tidak boleh kosong")
    private String noTelp;

    @NotEmpty(message = "Alamat kantor tidak boleh kosong")
    private String alamatKantor;

    @NotNull(message = "User tidak boleh kosong")
    private Long userId;

    @NotNull(message = "Status tidak boleh kosong")
    private Long statusId;

    @NotNull(message = "Pelatihan tidak boleh kosong")
    private Long pelatihanId;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getNamaPic() {
        return namaPic;
    }

    public void setNamaPic(String namaPic) {
        this.namaPic = namaPic;
    }

    public Date getTanggalPelatihan() {
        return tanggalPelatihan;
    }

    public void setTanggalPelatihan(Date tanggalPelatihan) {
        this.tanggalPelatihan = tanggalPelatihan;
    }

    public String getTempatPelatihan() {
        return tempatPelatihan;
    }

    public void setTempatPelatihan(String tempatPelatihan) {
        this.tempatPelatihan = tempatPelatihan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamatKantor() {
        return alamatKantor;
    }

    public void setAlamatKantor(String alamatKantor) {
        this.alamatKantor = alamatKantor;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getPelatihanId() {
        return pelatihanId;
    }

    public void setPelatihanId(Long pelatihanId) {
        this.pelatihanId = pelatihanId;
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
