package com.kkp.pelatihanwebservice.internal.models;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public MaritalStatus() {
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
