package com.kkp.pelatihanwebservice.internal.dto.certificate.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

public class CertificateRequest {
    @NotEmpty(message = "Kode sertifikat tidak boleh kosong")
    private String kodeSertifikat;

    @NotNull(message = "Tanggal mulai pelatihan tidak boleh kosong")
    private Date tanggalMulaiPelatihan;

    @NotNull(message = "Tanggal selesai pelatihan tidak boleh kosong")
    private Date tanggalSelesaiPelatihan;

    @NotNull(message = "Tanggal expired pelatihan tidak boleh kosong")
    private Date tanggalExpired;

    @NotEmpty(message = "Nama lembaga tidak boleh kosong")
    private String namaLembaga;

    @NotEmpty(message = "Lokasi tidak boleh kosong")
    private String lokasi;

    private String fileSertifikat;

    @NotNull(message = "ID Peserta tidak boleh kosong")
    private Long idPeserta;

    @NotNull(message = "ID Trainer tidak boleh kosong")
    private Long idTrainer;

    @NotNull(message = "ID Pelatihan tidak boleh kosong")
    private Long idPelatihan;

    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updatedAt = null;

    public String getKodeSertifikat() {
        return kodeSertifikat;
    }

    public void setKodeSertifikat(String kodeSertifikat) {
        this.kodeSertifikat = kodeSertifikat;
    }

    public Date getTanggalMulaiPelatihan() {
        return tanggalMulaiPelatihan;
    }

    public void setTanggalMulaiPelatihan(Date tanggalMulaiPelatihan) {
        this.tanggalMulaiPelatihan = tanggalMulaiPelatihan;
    }

    public Date getTanggalSelesaiPelatihan() {
        return tanggalSelesaiPelatihan;
    }

    public void setTanggalSelesaiPelatihan(Date tanggalSelesaiPelatihan) {
        this.tanggalSelesaiPelatihan = tanggalSelesaiPelatihan;
    }

    public Date getTanggalExpired() {
        return tanggalExpired;
    }

    public void setTanggalExpired(Date tanggalExpired) {
        this.tanggalExpired = tanggalExpired;
    }

    public String getNamaLembaga() {
        return namaLembaga;
    }

    public void setNamaLembaga(String namaLembaga) {
        this.namaLembaga = namaLembaga;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

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

    public Long getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(Long idTrainer) {
        this.idTrainer = idTrainer;
    }

    public Long getIdPelatihan() {
        return idPelatihan;
    }

    public void setIdPelatihan(Long idPelatihan) {
        this.idPelatihan = idPelatihan;
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
