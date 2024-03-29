package com.kkp.pelatihanwebservice.internal.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "agama")
public class Religion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kd_agama")
    private String kodeAgama;

    @Column(name = "nm_agama")
    private String namaAgama;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "agama")
    private Set<ParticipantInternal> participantInternal;

    public Religion(Long id, String kodeAgama, String namaAgama, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.kodeAgama = kodeAgama;
        this.namaAgama = namaAgama;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Religion() {
    }

    public Religion(String kode, String nama, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.kodeAgama = kode;
        this.namaAgama = nama;
        this.createdAt = createdAt;
        this.updatedAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeAgama() {
        return kodeAgama;
    }

    public void setKodeAgama(String kodeAgama) {
        this.kodeAgama = kodeAgama;
    }

    public String getNamaAgama() {
        return namaAgama;
    }

    public void setNamaAgama(String namaAgama) {
        this.namaAgama = namaAgama;
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
