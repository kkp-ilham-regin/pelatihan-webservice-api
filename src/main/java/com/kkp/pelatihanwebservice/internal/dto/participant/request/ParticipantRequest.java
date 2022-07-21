package com.kkp.pelatihanwebservice.internal.dto.participant.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

public class ParticipantRequest {
    @NotEmpty(message = "Nama Peserta tidak boleh kosong")
    private String namaPeserta;

    @NotEmpty(message = "NIP tidak boleh kosong")
    private String nip;

    private String nomorKta;

    private Date expiredKta;

    @NotEmpty(message = "Jabatan tidak boleh kosong")
    private String jabatan;

    @NotEmpty(message = "Lokasi Kerja tidak boleh kosong")
    private String lokasiKerja;

    @NotEmpty(message = "NIK tidak boleh kosong")
    private String nik;

    @NotNull(message = "Tanggal TMT masuk tidak boleh kosong")
    private Date tanggalTmtMasuk;

    @NotEmpty(message = "Alamat tidak boleh kosong")
    private String alamat;

    @NotEmpty(message = "Tempat Lahir tidak boleh kosong")
    private String tempatLahir;

    @NotNull(message = "Tanggal Lahir tidak boleh kosong")
    private Date tanggalLahir;

    @NotEmpty(message = "Golongan Darah tidak boleh kosong")
    private String golonganDarah;

    @NotEmpty(message = "Nomor Telepon tidak boleh kosong")
    private String nomorTelepon;

    private String namaSekolah;

    @NotEmpty(message = "Email tidak boleh kosong")
    @Email(message = "Email tidak valid")
    private String email;

    @NotEmpty(message = "NPWP tidak boleh kosong")
    private String npwp;

    private String urlImage;

    @NotNull(message = "Wilayah tidak boleh kosong")
    private Long idWilayah;

    @NotNull(message = "Jenis Kelamin tidak boleh kosong")
    private Long idJenisKelamin;

    @NotNull(message = "Agama tidak boleh kosong")
    private Long idAgama;

    @NotNull(message = "Status Pernikahan tidak boleh kosong")
    private Long idStatusPernikahan;

    @NotNull(message = "Pendidikan tidak boleh kosong")
    private Long idPendidikan;

    @NotNull(message = "Status Pegawai tidak boleh kosong")
    private Long idStatusPegawai;

    @NotNull(message = "Penawaran tidak boleh kosong")
    private Long idPenawaran;

    LocalDateTime createdAt;
    LocalDateTime updated;

    public String getNamaPeserta() {
        return namaPeserta;
    }

    public void setNamaPeserta(String namaPeserta) {
        this.namaPeserta = namaPeserta;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNomorKta() {
        return nomorKta;
    }

    public void setNomorKta(String nomorKta) {
        this.nomorKta = nomorKta;
    }

    public Date getExpiredKta() {
        return expiredKta;
    }

    public void setExpiredKta(Date expiredKta) {
        this.expiredKta = expiredKta;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getLokasiKerja() {
        return lokasiKerja;
    }

    public void setLokasiKerja(String lokasiKerja) {
        this.lokasiKerja = lokasiKerja;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getTanggalTmtMasuk() {
        return tanggalTmtMasuk;
    }

    public void setTanggalTmtMasuk(Date tanggalTmtMasuk) {
        this.tanggalTmtMasuk = tanggalTmtMasuk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Long getIdWilayah() {
        return idWilayah;
    }

    public void setIdWilayah(Long idWilayah) {
        this.idWilayah = idWilayah;
    }

    public Long getIdJenisKelamin() {
        return idJenisKelamin;
    }

    public void setIdJenisKelamin(Long idJenisKelamin) {
        this.idJenisKelamin = idJenisKelamin;
    }

    public Long getIdAgama() {
        return idAgama;
    }

    public void setIdAgama(Long idAgama) {
        this.idAgama = idAgama;
    }

    public Long getIdStatusPernikahan() {
        return idStatusPernikahan;
    }

    public void setIdStatusPernikahan(Long idStatusPernikahan) {
        this.idStatusPernikahan = idStatusPernikahan;
    }

    public Long getIdPendidikan() {
        return idPendidikan;
    }

    public void setIdPendidikan(Long idPendidikan) {
        this.idPendidikan = idPendidikan;
    }

    public Long getIdStatusPegawai() {
        return idStatusPegawai;
    }

    public void setIdStatusPegawai(Long idStatusPegawai) {
        this.idStatusPegawai = idStatusPegawai;
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

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
