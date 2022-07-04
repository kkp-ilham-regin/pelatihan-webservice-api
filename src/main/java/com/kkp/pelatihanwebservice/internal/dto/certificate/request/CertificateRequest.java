package com.kkp.pelatihanwebservice.internal.dto.certificate.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

public class CertificateRequest {

    private String fileSertifikat;

    @NotNull(message = "ID Peserta tidak boleh kosong")
    private Long idPeserta;

    @NotNull(message = "ID Penawaran tidak boleh kosong")
    private Long idPenawaran;

    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updatedAt = null;

    public String getFileSertifikat() {
        return fileSertifikat;
    }

    public void setFileSertifikat(String fileSertifikat) {
        this.fileSertifikat = fileSertifikat;
    }

    public Long getIdPeserta() {
        return idPeserta;
    }

    public void setIdPeserta(Long idPeserta) {
        this.idPeserta = idPeserta;
    }

    public Long getIdPenawaran() {
        return idPenawaran;
    }

    public void setIdPenawaran(Long idPenawaran) {
        this.idPenawaran = idPenawaran;
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
