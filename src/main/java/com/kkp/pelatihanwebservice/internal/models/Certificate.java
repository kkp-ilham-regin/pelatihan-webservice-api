package com.kkp.pelatihanwebservice.internal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "sertifikat")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kd_sertifikat")
    private String kodeSertifikat;

    @Column(name = "tgl_mulai_pelatihan")
    private Date tanggalMulaiPelatihan;

    @Column(name = "tgl_selesai_pelatihan")
    private Date tanggalSelesaiPelatihan;

    @Column(name = "tgl_expired")
    private Date tanggalExpired;

    @Column(name = "nm_lembaga")
    private String namaLembaga;

    @Column(name = "lokasi")
    private String lokasi;

    @Column(name = "file_sertifikat")
    private String fileSertifikat;

    @Column(name = "id_peserta")
    private Long idPeserta;

    @Column(name = "id_trainer")
    private Long idTrainer;

    @Column(name = "id_pelatihan")
    private Long idPelatihan;

    //    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "id_peserta", insertable = false, updatable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_peserta", insertable = false, updatable = false)
    @JsonIgnore
    private ParticipantInternal peserta = new ParticipantInternal();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_trainer", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trainer trainer = new Trainer();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pelatihan", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Training pelatihan = new Training();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Certificate() {
    }

    public Certificate(String kodeSertifikat, Date tanggalMulaiPelatihan, Date tanggalSelesaiPelatihan, Date tanggalExpired,
                       String namaLembaga, String lokasi, String fileSertifikat, Long idPeserta, Long idTrainer,
                       Long idPelatihan, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.kodeSertifikat = kodeSertifikat;
        this.tanggalMulaiPelatihan = tanggalMulaiPelatihan;
        this.tanggalSelesaiPelatihan = tanggalSelesaiPelatihan;
        this.tanggalExpired = tanggalExpired;
        this.namaLembaga = namaLembaga;
        this.lokasi = lokasi;
        this.fileSertifikat = fileSertifikat;
        this.idPeserta = idPeserta;
        this.idTrainer = idTrainer;
        this.idPelatihan = idPelatihan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Certificate(Long id, String kodeSertifikat, Date tanggalMulaiPelatihan, Date tanggalSelesaiPelatihan, Date tanggalExpired, String namaLembaga, String lokasi, String fileSertifikat, Long idPeserta, Long idTrainer, Long idPelatihan, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.kodeSertifikat = kodeSertifikat;
        this.tanggalMulaiPelatihan = tanggalMulaiPelatihan;
        this.tanggalSelesaiPelatihan = tanggalSelesaiPelatihan;
        this.tanggalExpired = tanggalExpired;
        this.namaLembaga = namaLembaga;
        this.lokasi = lokasi;
        this.fileSertifikat = fileSertifikat;
        this.idPeserta = idPeserta;
        this.idTrainer = idTrainer;
        this.idPelatihan = idPelatihan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ParticipantInternal getPeserta() {
        return peserta;
    }

    public void setPeserta(ParticipantInternal peserta) {
        this.peserta = peserta;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Training getPelatihan() {
        return pelatihan;
    }

    public void setPelatihan(Training pelatihan) {
        this.pelatihan = pelatihan;
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
}
