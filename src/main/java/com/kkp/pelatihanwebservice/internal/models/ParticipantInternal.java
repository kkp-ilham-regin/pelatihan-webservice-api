package com.kkp.pelatihanwebservice.internal.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "peserta")
public class ParticipantInternal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_peserta")
    private String namaPeserta;

    @Column(name = "nip")
    private String nip;

    @Column(name = "no_kta")
    private String nomorKta;

    @Column(name = "expired_kta")
    private Date expiredKta;

    @Column(name = "jabatan")
    private String jabatan;

    @Column(name = "lokasi_kerja")
    private String lokasiKerja;

    @Column(name = "nik")
    private String nik;

    @Column(name = "tgl_tmt_masuk")
    private Date tanggaTmtMasuk;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @Column(name = "tgl_lahir")
    private Date tanggalLahir;

    @Column(name = "gol_darah")
    private String golonganDarah;

    @Column(name = "no_telp")
    private String nomorTelepon;

    @Column(name = "nama_sekolah")
    private String namaSekolah;

    @Column(name = "email")
    private String email;

    @Column(name = "npwp")
    private String npwp;

    @Column(name = "url_image")
    private String urlImage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_wilayah", referencedColumnName = "id")
    private Area wilayah;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_jenis_kelamin", referencedColumnName = "id")
    private Gender jenisKelamin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_agama", referencedColumnName = "id")
    private Religion agama;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status_pernikahan", referencedColumnName = "id")
    private MaritalStatus statusPernikahan;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pendidikan", referencedColumnName = "id")
    private Education pendidikan;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status_pegawai", referencedColumnName = "id")
    private EmployeeStatus statusPegawai;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getTanggaTmtMasuk() {
        return tanggaTmtMasuk;
    }

    public void setTanggaTmtMasuk(Date tanggaTmtMasuk) {
        this.tanggaTmtMasuk = tanggaTmtMasuk;
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

    public Area getWilayah() {
        return wilayah;
    }

    public void setWilayah(Area wilayah) {
        this.wilayah = wilayah;
    }

    public Gender getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Gender jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Religion getAgama() {
        return agama;
    }

    public void setAgama(Religion agama) {
        this.agama = agama;
    }

    public MaritalStatus getStatusPernikahan() {
        return statusPernikahan;
    }

    public void setStatusPernikahan(MaritalStatus statusPernikahan) {
        this.statusPernikahan = statusPernikahan;
    }

    public Education getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(Education pendidikan) {
        this.pendidikan = pendidikan;
    }

    public EmployeeStatus getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(EmployeeStatus statusPegawai) {
        this.statusPegawai = statusPegawai;
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
