package com.kkp.pelatihanwebservice.internal.dto.response;

import java.time.LocalDateTime;

public class JwtResponse {
    private Long id;
    private String fullname;
    private String email;
    private LocalDateTime emailVerifiedAt;
    private String token;
    private boolean isAdmin;
    private String type = "Bearer";

    public JwtResponse(Long id, String fullname, String email, LocalDateTime emailVerifiedAt, boolean isAdmin, String token) {
        this.token = token;
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.emailVerifiedAt = emailVerifiedAt;
        this.isAdmin = isAdmin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(LocalDateTime emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
