package com.kkp.pelatihanwebservice.internal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Date tanggalTmtMasuk;

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

    @Column(name = "wilayah")
    private String wilayah;

    @Column(name = "id_jenis_kelamin")
    private Long idJenisKelamin;

    @Column(name = "id_agama")
    private Long idAgama;

    @Column(name = "id_status_pernikahan")
    private Long idStatusPernikahan;

    @Column(name = "pendidikan")
    private String pendidikan;

    @Column(name = "status_pegawai")
    private String statusPegawai;

    @Column(name = "id_penawaran")
    private Long idPenawaran;

//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "id_wilayah", insertable = false, updatable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Area wilayah;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_jenis_kelamin", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Gender jenisKelamin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_agama", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Religion agama;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_status_pernikahan", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MaritalStatus statusPernikahan;

//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "id_pendidikan", insertable = false, updatable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Education pendidikan;

//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "id_status_pegawai", insertable = false, updatable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private EmployeeStatus statusPegawai;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_penawaran", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Offer penawaran;

    //    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "id")
//    @JsonIgnore
//    @OneToMany(mappedBy = "peserta", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Set<Certificate> certificates = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    @JsonIgnore
    private Set<Certificate> certificates;

    public ParticipantInternal() {
    }

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ParticipantInternal(String namaPeserta, String nip, String nomorKta, Date expiredKta, String jabatan,
                               String lokasiKerja, String nik, Date tanggalTmtMasuk, String alamat, String tempatLahir,
                               Date tanggalLahir, String golonganDarah, String nomorTelepon, String namaSekolah, String email,
                               String npwp, String urlImage, String wilayah, Long idJenisKelamin, Long idAgama,
                               Long idStatusPernikahan, String pendidikan, String statusPegawai, Long idPenawaran,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
        this.namaPeserta = namaPeserta;
        this.nip = nip;
        this.nomorKta = nomorKta;
        this.expiredKta = expiredKta;
        this.jabatan = jabatan;
        this.lokasiKerja = lokasiKerja;
        this.nik = nik;
        this.tanggalTmtMasuk = tanggalTmtMasuk;
        this.alamat = alamat;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.golonganDarah = golonganDarah;
        this.nomorTelepon = nomorTelepon;
        this.namaSekolah = namaSekolah;
        this.email = email;
        this.npwp = npwp;
        this.urlImage = urlImage;
        this.wilayah = wilayah;
        this.idJenisKelamin = idJenisKelamin;
        this.idAgama = idAgama;
        this.idStatusPernikahan = idStatusPernikahan;
        this.pendidikan = pendidikan;
        this.statusPegawai = statusPegawai;
        this.idPenawaran = idPenawaran;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

//    public Area getWilayah() {
//        return wilayah;
//    }
//
//    public void setWilayah(Area wilayah) {
//        this.wilayah = wilayah;
//    }

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

//    public Education getPendidikan() {
//        return pendidikan;
//    }
//
//    public void setPendidikan(Education pendidikan) {
//        this.pendidikan = pendidikan;
//    }

//    public EmployeeStatus getStatusPegawai() {
//        return statusPegawai;
//    }
//
//    public void setStatusPegawai(EmployeeStatus statusPegawai) {
//        this.statusPegawai = statusPegawai;
//    }


    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
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

//    public Long getIdWilayah() {
//        return idWilayah;
//    }
//
//    public void setIdWilayah(Long idWilayah) {
//        this.idWilayah = idWilayah;
//    }

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

//    public Long getIdPendidikan() {
//        return idPendidikan;
//    }
//
//    public void setIdPendidikan(Long idPendidikan) {
//        this.idPendidikan = idPendidikan;
//    }
//
//    public Long getIdStatusPegawai() {
//        return idStatusPegawai;
//    }
//
//    public void setIdStatusPegawai(Long idStatusPegawai) {
//        this.idStatusPegawai = idStatusPegawai;
//    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }

    public Long getIdPenawaran() {
        return idPenawaran;
    }

    public void setIdPenawaran(Long idPenawaran) {
        this.idPenawaran = idPenawaran;
    }

    public Offer getPenawaran() {
        return penawaran;
    }

    public void setPenawaran(Offer penawaran) {
        this.penawaran = penawaran;
    }
}
