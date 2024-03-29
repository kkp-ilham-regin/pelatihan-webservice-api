package com.kkp.pelatihanwebservice.internal.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "pendidikan")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kd_pendidikan")
    private String kodePendidikan;

    @Column(name = "nm_pendidikan")
    private String namaPendidikan;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "pendidikan")
//    private Set<ParticipantInternal> participantInternal;

    public Education() {
    }

    public Education(String kode, String nama, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.kodePendidikan = kode;
        this.namaPendidikan = nama;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodePendidikan() {
        return kodePendidikan;
    }

    public void setKodePendidikan(String kodePendidikan) {
        this.kodePendidikan = kodePendidikan;
    }

    public String getNamaPendidikan() {
        return namaPendidikan;
    }

    public void setNamaPendidikan(String namaPendidikan) {
        this.namaPendidikan = namaPendidikan;
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
