package com.kkp.pelatihanwebservice.internal.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pelatihan")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kd_pelatihan")
    private String kode;

    @Column(name = "nm_pelatihan")
    private String nama;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Training() {
    }

    public Training(Long id, String kode, String nama, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Training(String nama, String kode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.nama = nama;
        this.kode = kode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
