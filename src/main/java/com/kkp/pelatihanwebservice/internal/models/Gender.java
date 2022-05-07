package com.kkp.pelatihanwebservice.internal.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jenis_kelamin")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kd_jenis_kelamin")
    private String kodeJenisKelamin;

    @Column(name = "nm_jenis_kelamin")
    private String namaJenisKelamin;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Gender(Long id, String kodeJenisKelamin, String namaJenisKelamin, LocalDateTime createdAt,
                  LocalDateTime updatedAt) {
        this.id = id;
        this.kodeJenisKelamin = kodeJenisKelamin;
        this.namaJenisKelamin = namaJenisKelamin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Gender() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeJenisKelamin() {
        return kodeJenisKelamin;
    }

    public void setKodeJenisKelamin(String kodeJenisKelamin) {
        this.kodeJenisKelamin = kodeJenisKelamin;
    }

    public String getNamaJenisKelamin() {
        return namaJenisKelamin;
    }

    public void setNamaJenisKelamin(String namaJenisKelamin) {
        this.namaJenisKelamin = namaJenisKelamin;
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
