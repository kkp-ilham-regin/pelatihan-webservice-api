package com.kkp.pelatihanwebservice.internal.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkp.pelatihanwebservice.internal.models.UserApi;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

public class UserApiDetailsImpl implements UserDetails {
    private static final Long serialVersionUID = 1L;

    private Long id;
    private String fullname;
    private String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime emailVerifiedAt;
    private boolean isAdmin;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserApiDetailsImpl(Long id, String fullname, String email, String password,
                              LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime emailVerifiedAt, boolean isAdmin) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.emailVerifiedAt = emailVerifiedAt;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public static UserApiDetailsImpl build(UserApi userApi) {

        return new UserApiDetailsImpl(userApi.getId(), userApi.getFullname(), userApi.getEmail(),
                userApi.getPassword(), userApi.getCreatedAt(),
                userApi.getUpdatedAt(), userApi.getEmailVerifiedAt(), userApi.isAdminStatus());
    }

    public Long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserApiDetailsImpl employeeDetails = (UserApiDetailsImpl) obj;
        return Objects.equals(id, employeeDetails.id);
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
