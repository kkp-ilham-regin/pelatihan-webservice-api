package com.kkp.pelatihanwebservice.internal.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "status_pernikahan")
public class MaritalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kd_status_pernikahan")
    private String kodeStatusPernikahan;

    @Column(name = "nm_status_pernikahan")
    private String namaStatusPernikahan;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "statusPernikahan")
    private Set<ParticipantInternal> participantInternal;

    public MaritalStatus() {
    }

    public MaritalStatus(String kode, String namaStatusPernikahan, LocalDateTime creadtedAt, LocalDateTime updatedAt) {
        this.kodeStatusPernikahan = kode;
        this.namaStatusPernikahan = namaStatusPernikahan;
        this.createdAt = creadtedAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeStatusPernikahan() {
        return kodeStatusPernikahan;
    }

    public void setKodeStatusPernikahan(String kodeStatusPernikahan) {
        this.kodeStatusPernikahan = kodeStatusPernikahan;
    }

    public String getNamaStatusPernikahan() {
        return namaStatusPernikahan;
    }

    public void setNamaStatusPernikahan(String namaStatusPernikahan) {
        this.namaStatusPernikahan = namaStatusPernikahan;
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
