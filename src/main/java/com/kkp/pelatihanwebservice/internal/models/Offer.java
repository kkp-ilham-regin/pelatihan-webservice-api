package com.kkp.pelatihanwebservice.internal.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "penawaran")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_perusahaan")
    private String namaPerusahaan;

    @Column(name = "nama_pic")
    private String namaPic;

    @Column(name = "hari_tanggal_pelatihan")
    private Date tanggalPelatihan;

    @Column(name = "tempat_pelatihan")
    private String tempatPelatihan;

    @Column(name = "email")
    private String email;

    @Column(name = "no_telp")
    private String noTelp;

    @Column(name = "alamat_kantor")
    private String alamatKantor;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "pelatihan_id")
    private Long pelatihanId;

    @Column(name = "trainer_id")
    private Long trainerId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserApi user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pelatihan_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Training training;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "trainer_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Status status;

    public Offer() {
    }

    public Offer(String namaPerusahaan, String namaPic, Date tanggalPelatihan, String tempatPelatihan, String email,
                 String noTelp, String alamatKantor, Long userId, Long pelatihanId, Long statusId, LocalDateTime createdAt,
                 LocalDateTime updatedAt) {
        this.namaPerusahaan = namaPerusahaan;
        this.namaPic = namaPic;
        this.tanggalPelatihan = tanggalPelatihan;
        this.tempatPelatihan = tempatPelatihan;
        this.email = email;
        this.noTelp = noTelp;
        this.alamatKantor = alamatKantor;
        this.userId = userId;
        this.pelatihanId = pelatihanId;
        this.statusId = statusId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Offer(Long statusId, Long trainerId, LocalDateTime updatedAt) {
        this.statusId = statusId;
        this.trainerId = trainerId;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserApi getUser() {
        return user;
    }

    public void setUser(UserApi user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
